package pizza.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pizza.services.CustomUserDetailsService;


@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Security extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService userDetailsService;



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http

                .authorizeRequests()
                .antMatchers("/", "/menu", "/order", "/log","/editProfile", "/reg", "/image/**",  "/static/**", "/css/**", "/js/**", "/font/**", "/img/**").permitAll()
                .antMatchers("/user/**").hasRole("USER") // Требовать роль USER для доступа к /profile
                .antMatchers("/admin/**").hasRole("ADMIN") // Требовать роль ADMIN для доступа к /admin
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/log")
                .usernameParameter("login") // Параметр имени пользователя в форме входа
                .passwordParameter("password") // Параметр пароля в форме входа
                .defaultSuccessUrl("/profile")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout") // Указать URL для выхода из системы
                .logoutSuccessUrl("/") // Указать страницу перенаправления после выхода из системы
                .permitAll();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(5);
    }

}
