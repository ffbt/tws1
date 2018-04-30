package sa.tws1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import sa.tws1.security.service.CustomUserService;
import sa.tws1.util.MD5Util;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    @Bean
    UserDetailsService customUserService()
    {
        return new CustomUserService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth.userDetailsService(customUserService()).passwordEncoder(new PasswordEncoder()
        {
            @Override
            public String encode(CharSequence rawPassword)
            {
                return MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword)
            {
                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
            }
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.authorizeRequests()
//                .antMatchers("/js/**","/css/**").permitAll()
                .antMatchers("/register","/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .usernameParameter("id")
                .loginPage("/login")
                .defaultSuccessUrl("/home")
                .failureUrl("/login?error").permitAll()
                .and()
                .csrf().disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception
    {
        web.ignoring().antMatchers("/css/**", "/js/**");    // 设置不拦截规则（静态）
    }
}
