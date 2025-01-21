package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grey.fable.base.collection.CollectionUtils;
import grey.fable.base.map.MapUtils;
import grey.fable.base.text.StringUtils;
import grey.fable.base.util.IdUtils;
import fan.dao.ArticleDAO;
import fan.pojo.entity.ArticleDO;
import fan.pojo.entity.CategoryDO;
import fan.pojo.query.ArticleQuery;
import fan.pojo.query.CategoryQuery;
import fan.service.ArticleService;
import fan.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Article interface implementation class
 *
 * @author Fan
 * @since 2024/2/22 14:41
 */
@Service
@Slf4j
public class ArticleServiceImpl implements ArticleService {

    @Value("${fan.image-path}")
    private String imagePath;

    private final ArticleDAO articleDAO;
    private final CategoryService categoryService;

    @Autowired
    public ArticleServiceImpl(ArticleDAO articleDAO, CategoryService categoryService) {
        this.articleDAO = articleDAO;
        this.categoryService = categoryService;
    }

    private static final String CATEGORY_ID = "category_id";
    private static final String WEBSITE = "img.fan223.cn/";

    @Override
    public Integer cleanInvalidImages() {
        Set<String> storedImages = listStoredImages();
        Set<String> validImages = listValidImages();
        storedImages.removeAll(validImages);

        int count = 0;
        for (String storedImage : storedImages) {
            // Skip wallpaper folder
            if (storedImage.startsWith("wallpaper")) {
                continue;
            }

            try {
                Files.delete(Paths.get(imagePath + storedImage));
                count++;
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
        return count;
    }

    /**
     * Retrieve stored images
     *
     * @return {@link Set<String>}
     * @author Fan
     * @since 2024/3/14 11:09
     */
    private Set<String> listStoredImages() {
        Set<String> storedImages = new HashSet<>();

        try (Stream<Path> walk = Files.walk(Paths.get(imagePath))) {
            walk.forEach(path -> {
                if (Files.isRegularFile(path)) {
                    storedImages.add(path.toString().substring(imagePath.length()).replace("\\", "/"));
                }
            });
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return storedImages;
    }

    /**
     * Retrieve valid images
     *
     * @return {@link Set<String>}
     * @author Fan
     * @since 2024/3/14 11:09
     */
    private Set<String> listValidImages() {
        List<ArticleDO> articleDOS = listArticles(null);
        Set<String> validImages = new HashSet<>();

        for (ArticleDO articleDO : articleDOS) {
            String content = articleDO.getContent();
            int index = 0;

            while ((index = content.indexOf(WEBSITE, index)) != -1) {
                int startIndex = index + WEBSITE.length();
                int endIndex = content.indexOf(")", startIndex);

                String imgUrl = content.substring(startIndex, endIndex);
                validImages.add(imgUrl);
                index = endIndex + 1;
            }

            String cover = articleDO.getCover();
            validImages.add(cover.substring(cover.indexOf(WEBSITE) + WEBSITE.length()));
        }
        return validImages;
    }

    @Override
    public File exportAllArticles() {
        List<ArticleDO> articleDOS = listArticles(null);
        Map<String, List<ArticleDO>> articles = articleDOS.stream().collect(Collectors.groupingBy(ArticleDO::getCategoryId));
        List<CategoryDO> categoryDOS = categoryService.listCategories(new CategoryQuery());
        Map<String, String> categories = categoryDOS.stream().collect(Collectors.toMap(CategoryDO::getId, CategoryDO::getName));

        File blogs = new File("blogs.zip");
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(blogs)))) {
            for (Map.Entry<String, List<ArticleDO>> entry : articles.entrySet()) {
                String folderName = categories.get(entry.getKey());

                for (ArticleDO articleDO : entry.getValue()) {
                    zipOutputStream.putNextEntry(new ZipEntry(folderName + "/" +
                            articleDO.getTitle().replaceAll("[\\\\/:*?\"<>|]", "") + ".md"));
                    zipOutputStream.write(articleDO.getContent().getBytes());
                    zipOutputStream.closeEntry();
                }
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }

        return blogs;
    }

    @Override
    public File batchExportArticles(List<String> ids) {
        List<ArticleDO> articleDOS = listArticles(ids);
        File blogs = new File("blogs.zip");

        try (ZipOutputStream zipOutputStream = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(blogs)))) {
            for (ArticleDO articleDO : articleDOS) {
                zipOutputStream.putNextEntry(new ZipEntry(
                        articleDO.getTitle().replaceAll("[\\\\/:*?\"<>|]", "") + ".md"));
                zipOutputStream.write(articleDO.getContent().getBytes());
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return blogs;
    }

    @Override
    public Page<ArticleDO> pageArticles(ArticleQuery articleQuery) {
        QueryWrapper<ArticleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(articleQuery.getCategoryId()), CATEGORY_ID, articleQuery.getCategoryId())
                .eq(StringUtils.isNotBlank(articleQuery.getFlag()), "flag", articleQuery.getFlag())
                .like(StringUtils.isNotBlank(articleQuery.getTitle()), "title", articleQuery.getTitle())
                .select("id", CATEGORY_ID, "title", "left(content, 200) as content", "cover", "flag", "create_time", "update_time")
                .orderByDesc("update_time");
        return articleDAO.selectPage(new Page<>(articleQuery.getCurrentPage(), articleQuery.getPageSize()), queryWrapper);
    }

    @Override
    public List<ArticleDO> listArticles(List<String> ids) {
        return articleDAO.selectList(new LambdaQueryWrapper<ArticleDO>().in(CollectionUtils.isNotEmpty(ids), ArticleDO::getId, ids));
    }

    @Override
    public ArticleDO getArticleById(String id) {
        return articleDAO.selectById(id);
    }

    @Override
    public Map<String, String> listCountsByCategories() {
        QueryWrapper<ArticleDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.select(CATEGORY_ID, "count(id) as count")
                .eq("flag", "Y")
                .groupBy(CATEGORY_ID);
        List<Map<String, Object>> maps = articleDAO.selectMaps(queryWrapper);

        Map<String, String> categoryCounts = MapUtils.hashMap(false);
        for (Map<String, Object> map : maps) {
            String categoryId = map.get(CATEGORY_ID).toString();
            String count = ((Long) map.get("count")).toString();
            categoryCounts.put(categoryId, count);
        }
        return categoryCounts;
    }

    @Override
    public ArticleDO saveArticle(ArticleDO articleDO) {
        if (StringUtils.isBlank(articleDO.getId())) {
            String snowflakeId = IdUtils.getSnowflakeNextIdStr();
            articleDO.setId(snowflakeId);
            articleDO.setCategoryId("14151607879553024");
            articleDO.setCover("https://img.fan223.cn/wallpaper/12.jpg");

            Timestamp now = Timestamp.valueOf(LocalDateTime.now());
            articleDO.setCreateTime(now);
            articleDO.setUpdateTime(now);

            articleDAO.insert(articleDO);
            return articleDO;
        }

        return updateArticle(articleDO);
    }

    @Override
    public ArticleDO updateArticle(ArticleDO articleDO) {
        articleDO.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        articleDAO.updateById(articleDO);
        return articleDO;
    }

    @Override
    public Integer batchDeleteArticles(List<String> ids) {
        return articleDAO.deleteBatchIds(ids);
    }
}