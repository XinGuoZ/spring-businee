package cc.tg.controller;


import cc.tg.model.vo.ResultVO;
import cc.tg.orm.entity.Article;
import cc.tg.service.IArticleService;
import cc.tg.tools.RedisUtils;
import cc.tg.tools.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xinguo
 * @since 2020-09-21
 */
@RestController
@RequestMapping("/article")
@Api
public class ArticleController {

    @Autowired
    private IArticleService articleService;

    @Autowired
    private RedisUtils redisUtils;

    @ApiModelProperty("新增内容")
    @PostMapping("/addArtcle")
    public ResultVO addArtcle(@RequestBody Article article){
        redisUtils.lSet("article", article);
        return ResultUtil.success(articleService.save(article));
    }

}
