package cc.tg.tools;

import cc.tg.orm.entity.UserInfo;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * Security工具类
 *
 * @author xinguoz
 * @create 2020-11-03 22:49
 **/
@UtilityClass
public class SecurityUtils {
    /**
     * 获取当前登入用户
     * @Author     xinguoz
     * @Date        2020/11/18 13:49
     * @Param
     * @return      cc.tg.orm.entity.UserInfo
     **/
    public UserInfo getCurrentHrUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (UserInfo) authentication.getPrincipal();
    }

    /**
     * 获取权限
     * @Author     xinguoz
     * @Date        2020/11/18 13:53
     * @Param
     * @return      org.springframework.security.core.Authentication
     **/
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
