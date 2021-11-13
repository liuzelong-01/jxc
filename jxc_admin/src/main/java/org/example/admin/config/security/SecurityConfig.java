package org.example.admin.config.security;

import org.example.admin.config.ClassPathTldsLoader;
import org.example.admin.filters.KaptchaCodeFilter;
import org.example.admin.pojo.User;
import org.example.admin.service.IRbacService;
import org.example.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootConfiguration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private JxcAuthenticationSuccessHandler jxcAuthenticationSuccessHandler;
    @Autowired
    private JxcAuthenticationFailedHandler jxcAuthenticationFailedHandler;
    @Resource
    private IUserService userService;
    @Resource
    private JxcLogoutSuccessHandler jxcLogoutSuccessHandler;
    @Autowired
    private KaptchaCodeFilter kaptchaCodeFilter;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private IRbacService rbacService;

    /**
     * 放行静态资源
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/css/**",
                "/error/**",
                "/images/**",
                "/js/**",
                "/lib/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    //禁用csrf
        http.csrf().disable()
                .addFilterBefore(kaptchaCodeFilter, UsernamePasswordAuthenticationFilter.class)
                //允许iframe页面嵌套
                .headers().frameOptions().disable()
                .and()
                    .formLogin()
                    .usernameParameter("userName")
                    .passwordParameter("password")
                    .loginPage("/index")
                    .loginProcessingUrl("/login")
                    .successHandler(jxcAuthenticationSuccessHandler)
                    .failureHandler(jxcAuthenticationFailedHandler)
                .and()
                .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .logoutSuccessHandler(jxcLogoutSuccessHandler)
                .and()
                .rememberMe()
                .rememberMeParameter("rememberMe")
                .rememberMeCookieName("remember-me-cookie")
                .tokenValiditySeconds(7*24*60*60)
                .tokenRepository(persistentTokenRepository())
                .and()
                    .authorizeRequests().antMatchers("/index","/login","/image").permitAll()
                    .anyRequest().authenticated();


    }
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository =new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        return tokenRepository;
    }


    @Override
    @Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
                User userDetails=userService.findUserByUserName(s);
                /**
                 * 1.查询用户分配的角色
                 * 2.根据用户扮演的角色查询角色拥有的权限
                 */
                List<String> roleNames=rbacService.findRolesByUserName(s);
                List<String> authorities =rbacService.findAuthoritiesByRoleName(roleNames);
                roleNames=roleNames.stream().map(role->"ROLE_"+role).collect(Collectors.toList());
                authorities.addAll(roleNames);
                userDetails.setAuthorities(AuthorityUtils.commaSeparatedStringToAuthorityList(String.join(",",authorities)));
                return userDetails;
            }
        };
    }
    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(encoder());
    }


    @Bean
    @ConditionalOnMissingBean(ClassPathTldsLoader.class)
    public ClassPathTldsLoader classPathTldsLoader(){
        return new ClassPathTldsLoader();
    }



}
