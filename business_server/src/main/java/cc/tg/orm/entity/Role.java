package cc.tg.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2020-11-02
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String name;

    /**
     * 角色名称
     */
    @TableField("nameZh")
    private String nameZh;

    @ApiModelProperty(hidden = true)
    private String remark;

    /**
     * 删除状态 0-未删除 1-已删除
     */
    @ApiModelProperty(hidden = true)
    private String removeStatus;

    /**
     * 创建时间
     */
    @ApiModelProperty(hidden = true)
    private String createAt;

    /**
     * 权限组id
     */
    @ApiModelProperty(name="权限组id")
    private Long groupId;

    /**
     * 是否为管理
     */
    @ApiModelProperty(name="管理员")
    private boolean superAdmin;

}
