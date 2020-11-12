package cc.tg.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinguo
 * @since 2020-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Classification implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 名字
     */
    private String name;

    /**
     * 排序
排序
     */
    private Integer sort;

    /**
     * 路由
     */
    private String router;

    /**
     * 备注
     */
    private String remark;

    /**
     * 删除状态 0-未删除 1-已删除
     */
    private String removeStatus;

    /**
     * 创建时间
     */
    private String createAt;

    /**
     * 路径
     */
    private String path;

    /**
     * 组件
     */
    private String components;

    private String meta;

    private String title;


}
