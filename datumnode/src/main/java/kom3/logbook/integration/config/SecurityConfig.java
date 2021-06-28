package kom3.logbook.integration.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Properties;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Value("${report.book.etc}")
    String etcPath;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(check -> {
            Properties users = new Properties();
            try {
                users.load(new FileInputStream(Paths.get(etcPath, "systems.properties").toFile()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (users.containsKey(check))
                return new User(check, "{noop}" + String.valueOf(users.get(check)), Collections.singleton(new SimpleGrantedAuthority("ADMIN")));

            throw new UsernameNotFoundException("Invalid username");
        });
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
        ;
    }

}