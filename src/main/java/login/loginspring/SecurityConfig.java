package login.loginspring;

import login.loginspring.service.MemberService;
import org.apache.tomcat.util.http.parser.Authorization;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig{
    private final MemberService memberService;

    public SecurityConfig(MemberService memberService) {
        this.memberService = memberService;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    public void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                    .antMatchers("/login", "/signUp", "/access_denied").permitAll()
                    .antMatchers("/todolist", "/profile_edit", "/menu", "/goals", "/goals/new", "/challengeList", "/challenges/new").hasRole("USER")
                    .antMatchers("/userAccess").hasRole("ADMIN")
                    .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/login_proc")
                    .defaultSuccessUrl("/todolist")
                    .failureUrl("/access_denied")
                    .and()
                .logout()
                    .logoutUrl("/logout_proc")
                    .logoutSuccessUrl("/login")
                    .and()
                .csrf().disable();
//                .rememberMe()
//                .key("key")
//                .tokenValiditySeconds(86400*30) //달
//                .rememberMeParameter("remember-me");

        return httpSecurity.build();
    }
}
