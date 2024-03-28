package fan.controller;

import fan.core.Response;
import fan.pojo.entity.CategoryDO;
import fan.pojo.query.CategoryQuery;
import fan.pojo.vo.CategoryVO;
import fan.service.ArticleService;
import fan.service.CategoryService;
import fan.utils.BlogMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Category controller
 *
 * @author Fan
 * @since 2024/2/22 17:20
 */
@RestController
@RequestMapping("/blog/category")
public class CategoryController {

    private final CategoryService categoryService;
    private final ArticleService articleService;
    private final BlogMapStruct blogMapStruct;

    @Autowired
    public CategoryController(CategoryService categoryService, ArticleService articleService, BlogMapStruct blogMapStruct) {
        this.categoryService = categoryService;
        this.articleService = articleService;
        this.blogMapStruct = blogMapStruct;
    }

    @GetMapping("/listCategories")
    public Response<List<CategoryVO>> listCategories(CategoryQuery categoryQuery) {
        List<CategoryDO> categoryDos = categoryService.listCategories(categoryQuery);
        List<CategoryVO> categoryVos = blogMapStruct.transCategories(categoryDos);

        Map<String, String> map = articleService.listCountsByCategories();
        categoryVos.forEach(categoryVO -> categoryVO.setCount(map.get(categoryVO.getId())));
        return Response.success(categoryVos);
    }
}