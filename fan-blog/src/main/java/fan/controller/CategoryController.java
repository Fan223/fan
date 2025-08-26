package fan.controller;

import fan.pojo.dto.CategoryDTO;
import fan.pojo.entity.CategoryDO;
import fan.pojo.query.CategoryQuery;
import fan.pojo.vo.CategoryVO;
import fan.service.ArticleService;
import fan.service.CategoryService;
import fan.utils.BlogMapStruct;
import fancy.toolkit.collection.CollectionUtils;
import fancy.toolkit.net.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/listCategoryTree")
    public Response<List<CategoryVO>> listCategoryTree(CategoryQuery categoryQuery) {
        List<CategoryDO> categoryDos = categoryService.listCategories(categoryQuery);
        List<CategoryVO> categoryVos = blogMapStruct.transCategories(categoryDos);

        Map<String, String> map = articleService.listCountsByCategories();
        categoryVos.forEach(categoryVO -> categoryVO.setCount(map.get(categoryVO.getId())));
        return Response.success(buildTree(categoryVos));
    }

    public static List<CategoryVO> buildTree(List<CategoryVO> categoryVos) {
        if (CollectionUtils.isEmpty(categoryVos)) {
            return new ArrayList<>();
        }
        List<CategoryVO> menusTree = new ArrayList<>(categoryVos.size());

        // Convert the menu list into a Map, with the Key being the ID.
        Map<String, CategoryVO> CategoryVOMap = categoryVos.stream().collect(Collectors.toMap(CategoryVO::getId, CategoryVO -> CategoryVO));

        // Loop through the menu list
        for (CategoryVO CategoryVO : categoryVos) {
            // Determine if the current menu has a parent menu
            CategoryVO parentCategoryVO = CategoryVOMap.get(CategoryVO.getParentId());

            // If the parent menu is empty, the current menu is a top-level menu, directly added to the result list.
            if (null == parentCategoryVO) {
                menusTree.add(CategoryVO);
                continue;
            }

            // If it is not empty, add the current menu to the submenus of the obtained parent menu.
            parentCategoryVO.getChildren().add(CategoryVO);
        }

        return menusTree;
    }

    @PostMapping("/addCategory")
    public Response<String> addCategory(@RequestBody CategoryDTO categoryDTO) {
        CategoryDO categoryDO = blogMapStruct.transCategories(categoryDTO);
        categoryService.addCategory(categoryDO);
        return Response.success();
    }
}