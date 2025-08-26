package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import fan.dao.CategoryDAO;
import fan.pojo.entity.CategoryDO;
import fan.pojo.query.CategoryQuery;
import fan.service.CategoryService;
import fancy.toolkit.id.IdUtils;
import fancy.toolkit.text.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Category interface implementation class
 *
 * @author Fan
 * @since 2024/2/22 17:21
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryDAO categoryDAO;

    @Autowired
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<CategoryDO> listCategories(CategoryQuery categoryQuery) {
        LambdaQueryWrapper<CategoryDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(categoryQuery.getParentId()), CategoryDO::getParentId, categoryQuery.getParentId())
                .eq(StringUtils.isNotBlank(categoryQuery.getFlag()), CategoryDO::getFlag, categoryQuery.getFlag())
                .like(StringUtils.isNotBlank(categoryQuery.getName()), CategoryDO::getName, categoryQuery.getName())
                .orderByAsc(CategoryDO::getOrderNum);
        return categoryDAO.selectList(queryWrapper);
    }

    @Override
    public void addCategory(CategoryDO categoryDO) {
        String snowflakeIdStr = IdUtils.getSnowflakeIdStr();
        categoryDO.setId(snowflakeIdStr);

        LocalDateTime now = LocalDateTime.now();
        categoryDO.setCreateTime(Timestamp.valueOf(now));
        categoryDO.setUpdateTime(Timestamp.valueOf(now));
        categoryDAO.insert(categoryDO);
    }
}