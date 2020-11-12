package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Classification;
import cc.tg.service.IClassificationService;
import cc.tg.tools.ResultUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-09-24
 */
@Api("栏目")
@RestController
@RequestMapping("/classification")
public class ClassificationController {

    @Autowired
    private IClassificationService classificationService;

    @PostMapping("getAllClassificaton")
    @ApiModelProperty(value = "获取栏目")
    public ResultVO getAllClassificaton() {
        return ResultUtil.success(classificationService.list(new LambdaQueryWrapper<Classification>().select().eq(Classification::getRemoveStatus,"0")));
    }
}
