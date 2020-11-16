package cc.tg.orm.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
public class UserInfo implements UserDetails {

    private static final long serialVersionUID = 1L;

    /**
     * hrID
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 姓名
     */
    @ApiModelProperty( name = "姓名",example = "张三")
    @NotBlank(message = "姓名不能为空")
    private String name;

    /**
     * 手机号码
     */
    @ApiModelProperty( name = "手机号码",example = "1325")
    @NotBlank(message = "手机号码不能为空")
    private String phone;

    /**
     * 住宅电话
     */
    @ApiModelProperty( name = "住宅电话号码",example = "11323")
    @NotBlank(message = "住宅电话不能为空")
    private String telephone;

    /**
     * 联系地址
     */
    @ApiModelProperty( name = "联系地址",example = "湖南长沙")
    private String address;

    @Getter(value = AccessLevel.NONE)
    @ApiModelProperty(hidden = true)
    private Boolean enabled = true;

    /**
     * 用户名
     */
    @ApiModelProperty( name = "用户名",example = "super")
    @NotBlank(message = "住宅电话不能为空")
    private String username;

    /**
     * 密码
     */
    private String password;

    private String userface;

    /**
     * 备注
     */
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
     *分组id
     * */
    @ApiModelProperty(hidden = true)
    private Integer groupId;

    @TableField(exist = false)
    private List<Role> roles;

    @ApiModelProperty(value = "用户角色")
    @TableField(exist = false)
    private List<Long> roleIds;


    @JsonIgnore
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
