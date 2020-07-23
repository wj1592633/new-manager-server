package com.wj.manager.security.config;

import com.wj.manager.filter.JWTFilter;
import com.wj.manager.filter.SmsCodeFilter;
import com.wj.manager.filter.ValidateCodeFilter;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.security.constant.SecurityConstant;
import com.wj.manager.security.phone.SmsAuthenticationFilter;
import com.wj.manager.security.phone.SmsAuthenticationProvider;
import com.wj.manager.util.ServletUtil;
import com.wj.manager.util.SpringContextAware;
import com.wj.manager.vercode.store.ValidateCodeStorer;
import com.wj.manager.vo.ResponseResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;
import org.springframework.util.AntPathMatcher;

import javax.servlet.ServletException;
import java.util.Arrays;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //public final static String LOGIN_PROCESSING_URL = "/login";

    //配置了这个bean就相当于给SpringSecurity配置了PasswordEncoder
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    SecurityProperties properties;
    @Autowired
    AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler authenticationFailureHandler;
    @Autowired
    ValidateCodeStorer validateCodeStorer;

   /* @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        //ProviderManager
        AuthenticationManager authenticationManager = super.authenticationManagerBean();
        return authenticationManager;
    }*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // AuthenticationManager authenticationManager = http.getSharedObject(AuthenticationManager.class);
        JWTFilter jwtFilter = new JWTFilter();
        jwtFilter.setPathMatcher(antPathMatcher());

        ValidateCodeFilter codeFilter = validateCodeFilter();

        SmsAuthenticationFilter smsAuthenticationFilter = smsAuthenticationFilter();

        http

                .addFilterAfter(smsAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)

                .addFilterAfter(jwtFilter, SecurityContextPersistenceFilter.class)
                //.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(codeFilter, JWTFilter.class)
                .formLogin()
                .loginPage(SecurityConstant.LOGIN_PAGE)
                .loginProcessingUrl(SecurityConstant.LOGIN_PROCESSING_URL)//表单提交的url如果指定了loginPage，这个必须得配置.
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                //.and().exceptionHandling()//异常处理
                //.authenticationEntryPoint()//需要登陆时的处理
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessHandler((req, res, auth) -> {
                    ServletUtil.writeJsonData(req, res, ResponseResultHelper.buildOkResult(SecurityConstant.LOGOUT_SUCCESS_MSG));
                })
                .and()
                .authorizeRequests()
                .antMatchers(SecurityConstant.PERMIT_URL)
                .permitAll()
                .anyRequest()
                .permitAll()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //这里的忽略与上面不同，这里会完全不经过security的过滤器。
        //WebSecurity.IgnoredRequestConfigurer
        // 填了表单登陆提交的"/login"会导致SecurityContext为空，
        // 因为跳过SecurityContextPersistenceFilter，
        // 该过滤器会从session获取SecurityContext并设置到线程中
        web.ignoring().antMatchers(SecurityConstant.IGNORE_URL);
    }

    /*  @Override
      protected void configure(AuthenticationManagerBuilder auth) throws Exception {
          auth.userDetailsService().passwordEncoder()
          super.configure(auth);
      }*/
    @Bean
    public SmsAuthenticationProvider smsAuthenticationProvider() {
        SmsAuthenticationProvider provider = new SmsAuthenticationProvider();
        provider.setUserDetailsService(SpringContextAware.getBean(UserDetailsService.class));
        return provider;
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(SpringContextAware.getBean(UserDetailsService.class));
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public AntPathMatcher antPathMatcher(){
        return new AntPathMatcher();
    }

    private SmsCodeFilter smsCodeFilter(){
        SmsCodeFilter smsCodeFilter = new SmsCodeFilter();
        smsCodeFilter.setPathMatcher(antPathMatcher());
        smsCodeFilter.setFailHandler(authenticationFailureHandler);
        smsCodeFilter.setSecurityProperties(properties);
        return smsCodeFilter;
    }

    private ValidateCodeFilter validateCodeFilter() throws ServletException {
        ValidateCodeFilter codeFilter = new ValidateCodeFilter();
        codeFilter.setPathMatcher(antPathMatcher());
        codeFilter.setFailHandler(authenticationFailureHandler);
        codeFilter.setSecurityProperties(properties);
        codeFilter.setValidateCodeStorer(validateCodeStorer);
        codeFilter.afterPropertiesSet();
        return codeFilter;
    }

    private SmsAuthenticationFilter smsAuthenticationFilter() {
        SmsAuthenticationFilter smsAuthenticationFilter = new SmsAuthenticationFilter();
        smsAuthenticationFilter.setAuthenticationManager(new ProviderManager(Arrays.asList(smsAuthenticationProvider())));
        smsAuthenticationFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        smsAuthenticationFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        return smsAuthenticationFilter;
    }
}
