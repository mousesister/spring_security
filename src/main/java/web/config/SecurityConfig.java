package web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import web.config.handler.LoginSuccessHandler;
import web.service.MyUserDetailsService;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final MyUserDetailsService myUserDetailsService;
    private final LoginSuccessHandler loginSuccessHandler;

    public SecurityConfig(MyUserDetailsService myUserDetailsService, LoginSuccessHandler loginSuccessHandler) {
        this.myUserDetailsService = myUserDetailsService;
        this.loginSuccessHandler = loginSuccessHandler;
    }


    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);

        http.authorizeRequests()
                .antMatchers("/login", "/logout", "/hello").permitAll()
                //.antMatchers("/user", "/show").authenticated()
                .antMatchers(("/user")).hasAnyRole("USER", "ADMIN")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and()
            .formLogin()
                .successHandler(loginSuccessHandler)
                .and().logout()
            .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .and().csrf().disable()
//                .and()
                .addFilterBefore(filter, CsrfFilter.class);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
