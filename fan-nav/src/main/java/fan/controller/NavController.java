package fan.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import fan.core.Response;
import fan.pojo.dto.NavDTO;
import fan.pojo.entity.NavDO;
import fan.pojo.query.NavQuery;
import fan.pojo.vo.NavVO;
import fan.service.NavService;
import fan.utils.NavMapStruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Resource navigation controller
 *
 * @author Fan
 * @since 2024/3/27 10:51
 */
@RestController
@RequestMapping("/nav")
public class NavController {

    private final NavService navService;
    private final NavMapStruct navMapStruct;

    @Autowired
    public NavController(NavService navService, NavMapStruct navMapStruct) {
        this.navService = navService;
        this.navMapStruct = navMapStruct;
    }

    @GetMapping("/pageNavs")
    public Response<Page<NavVO>> pageNavs(NavQuery navQuery) {
        Page<NavDO> navPage = navService.pageNavs(navQuery);
        return Response.success(navMapStruct.transNav(navPage));
    }

    @PostMapping("/addNav")
    public Response<Integer> addNav(@RequestBody NavDTO navDTO) {
        NavDO navDO = navMapStruct.transNav(navDTO);
        return Response.success(navService.addNav(navDO));
    }

    @PutMapping("/updateNav")
    public Response<Integer> updateNav(@RequestBody NavDTO navDTO) {
        NavDO navDO = navMapStruct.transNav(navDTO);
        return Response.success(navService.updateNav(navDO));
    }

    @DeleteMapping("/batchDeleteNavs/{id}")
    public Response<Integer> batchDeleteNavs(@PathVariable("id") List<String> ids) {
        return Response.success(navService.batchDeleteNavs(ids));
    }
}