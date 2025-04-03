package fan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grey.fable.base.net.Response;
import fan.pojo.dto.ArticleDTO;
import fan.pojo.entity.ArticleDO;
import fan.pojo.query.ArticleQuery;
import fan.pojo.vo.ArticleVO;
import fan.service.ArticleService;
import fan.utils.BlogMapStruct;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Article controller
 *
 * @author Fan
 * @since 2024/2/22 14:05
 */
@RestController
@RequestMapping("/blog/article")
public class ArticleController {

    private final ArticleService articleService;
    private final BlogMapStruct blogMapStruct;

    @Autowired
    public ArticleController(ArticleService articleService, BlogMapStruct blogMapStruct) {
        this.articleService = articleService;
        this.blogMapStruct = blogMapStruct;
    }

    @GetMapping("/cleanInvalidImages")
    public Response<Integer> cleanInvalidImages() {
        return Response.success("清除失效的图片成功", articleService.cleanInvalidImages());
    }

    @GetMapping("/exportAllArticles")
    public void exportAllArticles(HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=blogs.zip");

        File file = articleService.exportAllArticles();
        ServletOutputStream outputStream = response.getOutputStream();
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[1024];
            int len;
            while ((len = fileInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        }
    }

    @GetMapping("/batchExportArticles/{id}")
    public void batchExportArticles(@PathVariable("id") List<String> ids, HttpServletResponse response) throws IOException {
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        ServletOutputStream outputStream = response.getOutputStream();

        if (1 == ids.size()) {
            ArticleDO articleDO = articleService.getArticleById(ids.getFirst());

            String encodeTitle = URLEncoder.encode(articleDO.getTitle().replaceAll("[\\\\/:*?\"<>|]", "") + ".md",
                    StandardCharsets.UTF_8).replace("+", "%20");
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + encodeTitle);

            outputStream.write(articleDO.getContent().getBytes());
        } else {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=blogs.zip");

            File file = articleService.batchExportArticles(ids);
            try (FileInputStream fileInputStream = new FileInputStream(file)) {
                byte[] bytes = new byte[1024];
                int len;
                while ((len = fileInputStream.read(bytes)) != -1) {
                    outputStream.write(bytes, 0, len);
                }
            }
        }
    }

    @GetMapping("/pageArticles")
    public Response<Page<ArticleVO>> pageArticles(ArticleQuery articleQuery) {
        Page<ArticleDO> doPage = articleService.pageArticles(articleQuery);
        Page<ArticleVO> voPage = blogMapStruct.transArticles(doPage);
        return Response.success(voPage);
    }

    @GetMapping("/listArticles/{id}")
    public Response<List<ArticleVO>> listArticles(@PathVariable("id") List<String> ids) {
        List<ArticleDO> articleDOS = articleService.listArticles(ids);
        return Response.success(blogMapStruct.transArticles(articleDOS));
    }

    @GetMapping("/listRecommendArticles")
    public Response<List<ArticleVO>> listRecommendArticles() {
        return Response.success(blogMapStruct.transArticles(articleService.listRecommendArticles()));
    }

    @GetMapping("/getArticle/{id}")
    public Response<ArticleVO> getArticleById(@PathVariable("id") String id) {
        ArticleDO articleDO = articleService.getArticleById(id);
        // 增加浏览量
        articleService.incrementView(id);
        return Response.success(blogMapStruct.transArticles(articleDO));
    }

    @PostMapping("/saveArticle")
    public Response<ArticleVO> saveArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDO articleDO = blogMapStruct.transArticles(articleDTO);
        ArticleDO article = articleService.saveArticle(articleDO);
        return Response.success(blogMapStruct.transArticles(article));
    }

    @PutMapping("/updateArticle")
    public Response<ArticleVO> updateArticle(@RequestBody ArticleDTO articleDTO) {
        ArticleDO articleDO = blogMapStruct.transArticles(articleDTO);
        ArticleDO article = articleService.updateArticle(articleDO);
        return Response.success(blogMapStruct.transArticles(article));

    }

    @DeleteMapping("/batchDeleteArticles/{id}")
    public Response<Integer> batchDeleteArticles(@PathVariable("id") List<String> ids) {
        return Response.success(articleService.batchDeleteArticles(ids));
    }
}