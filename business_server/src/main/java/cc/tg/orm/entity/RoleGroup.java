package cc.tg.orm.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author xinguo
 * @since 2020-11-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class RoleGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;

    /**
     * 权限组名字
     */
    private String name;

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


}
