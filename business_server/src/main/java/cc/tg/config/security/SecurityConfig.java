package cc.tg.config.security;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import cc.tg.config.exception.SystemErrorType;
import cc.tg.model.vo.ResultVO;
import cc.tg.service.impl.UserInfoServiceImpl;
import cc.tg.tools.ResultUtil;
import cc.tg.tools.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyPasswordEncoder myPasswordEncoder;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    public UserInfoServiceImpl iUserInfoService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(iUserInfoService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authenticationProvider(authenticationProvider())
                .httpBasic()
                //未登录时，进行json格式的提示，很喜欢这种写法，不用单独写一个又一个的类
                .authenticationEntryPoint((request,response,authException) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(ResultUtil.error(SystemErrorType.NOT_LOGIN)));
                    out.flush();
                    out.close();
                })

                .and()
                .authorizeRequests()
                .anyRequest().authenticated() //必须授权才能范围

                .and()
                .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password") //使用自带的登录
                .permitAll()
                //登录失败，返回json
                .failureHandler((request,response,ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    PrintWriter out = response.getWriter();
                    ResultVO res = new ResultVO();
                    if (ex instanceof UsernameNotFoundException || ex instanceof BadCredentialsException) {
                        res = ResultUtil.error(SystemErrorType.NOT_EXIST_USERNAME);
                    } else if (ex instanceof DisabledException) {
                        res = ResultUtil.error(SystemErrorType.USERNAME_DISABLE);
                    } else {
                        res = ResultUtil.error(SystemErrorType.LOGIN_ERROR);
                    }
                    out.write(objectMapper.writeValueAsString(res));
                    out.flush();
                    out.close();
                })
                //登录成功，返回json
                .successHandler((request,response,authentication) -> {

                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(ResultUtil.success(SecurityUtils.getCurrentHrUser())));
                    out.flush();
                    out.close();
                })
                .and()
                .exceptionHandling()
                //没有权限，返回json
                .accessDeniedHandler((request,response,ex) -> {
                    response.setContentType("application/json;charset=utf-8");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    PrintWriter out = response.getWriter();

                    out.write(objectMapper.writeValueAsString(ResultUtil.success(SystemErrorType.INSUFFICIENT_PERMISSIONS)));
                    out.flush();
                    out.close();
                })
                .and()
                .logout()
                //退出成功，返回json
                .logoutSuccessHandler((request,response,authentication) -> {

                    response.setContentType("application/json;charset=utf-8");
                    PrintWriter out = response.getWriter();
                    out.write(objectMapper.writeValueAsString(ResultUtil.success(SystemErrorType.LOGOUT_SUCCESS)));
                    out.flush();
                    out.close();
                })
                .permitAll();
        //开启跨域访问
        http.cors().disable();
        //开启模拟请求，比如API POST测试工具的测试，不开启时，API POST为报403错误
        http.csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) {
        //对于在header里面增加token等类似情况，放行所有OPTIONS请求。
        web.ignoring().antMatchers("/v2/api-docs","/swagger-resources/**","/swagger-ui.html","/webjars/**");
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //对默认的UserDetailsService进行覆盖
        authenticationProvider.setUserDetailsService(iUserInfoService);
        authenticationProvider.setPasswordEncoder(myPasswordEncoder);
        return authenticationProvider;
    }

}