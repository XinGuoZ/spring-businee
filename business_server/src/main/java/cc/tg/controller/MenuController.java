package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Menu;
import cc.tg.service.IMenuService;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-11-03
 */
@RestController
@RequestMapping("/tg/menu")
@Api(value = "菜单控制",tags = {"菜单"})
public class MenuController {

    @Autowired
    IMenuService menuService;

    @ApiModelProperty("获取菜单")
    @GetMapping("/sysmenu")
    public ResultVO sysmenu() {
        return ResultUtil.success( menuService.getMenusByUserId());
    }

    @ApiModelProperty("获取菜单树")
    @GetMapping("/menuTree")
    public ResultVO getMenuTree() {
        return ResultUtil.success(menuService.getMenuTree());
    }

    @ApiModelProperty("添加和修改菜单")
    @GetMapping("/addorEditMenu")
    public ResultVO addorEditMenu(@Valid @RequestBody Menu menu) {

        return ResultUtil.success(menuService.addorEditMenu(menu));
    }
}
