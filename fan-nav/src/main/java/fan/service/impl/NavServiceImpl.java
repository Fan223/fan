package fan.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import grey.fable.base.text.StringUtils;
import grey.fable.base.util.IdUtils;
import fan.dao.NavDAO;
import fan.pojo.entity.NavDO;
import fan.pojo.query.NavQuery;
import fan.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Resource navigation interface implementation class
 *
 * @author Fan
 * @since 2024/3/27 10:55
 */
@Service
public class NavServiceImpl implements NavService {

    private final NavDAO navDAO;

    @Autowired
    public NavServiceImpl(NavDAO navDAO) {
        this.navDAO = navDAO;
    }

    @Override
    public Page<NavDO> pageNavs(NavQuery navQuery) {
        LambdaQueryWrapper<NavDO> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(StringUtils.isNotBlank(navQuery.getName()), NavDO::getName, navQuery.getName())
                .eq(StringUtils.isNotBlank(navQuery.getType()), NavDO::getType, navQuery.getType())
                .orderByAsc(NavDO::getName);

        return navDAO.selectPage(new Page<>(navQuery.getCurrentPage(), navQuery.getPageSize()), queryWrapper);
    }

    @Override
    public Integer addNav(NavDO navDO) {
        navDO.setId(IdUtils.getSnowflakeNextIdStr());

        Timestamp now = Timestamp.valueOf(LocalDateTime.now());
        navDO.setCreateTime(now);
        navDO.setUpdateTime(now);
        return navDAO.insert(navDO);
    }

    @Override
    public Integer updateNav(NavDO navDO) {
        navDO.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
        return navDAO.updateById(navDO);
    }

    @Override
    public Integer batchDeleteNavs(List<String> ids) {
        return navDAO.deleteBatchIds(ids);
    }
}