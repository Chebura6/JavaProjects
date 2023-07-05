package Lab3.SpringData.Configs;


import Lab3.SpringData.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    public UserService userService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/api/catOwner/getAll").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/catOwner/createNew").hasRole("ADMIN")
                .antMatchers("/api/catOwner/find/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/catOwner/update/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/catOwner/delete/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/catOwner/getByName/**").hasAnyRole("USER", "ADMIN")

                .antMatchers("/api/cat/getAll").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/cat/createNew").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/cat/find/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/cat/update/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/cat/delete/**").hasAnyRole("USER", "ADMIN")

                .antMatchers("/api/flea/getAll").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/flea/createNew").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/flea/find/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/flea/update/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/flea/delete/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/flea/findFleasByCatId/**").hasAnyRole("USER", "ADMIN")
                .antMatchers("/swagger-ui/").authenticated()
                .and()
                .formLogin().defaultSuccessUrl("/swagger-ui/")
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);

        return authenticationProvider;
    }
}
