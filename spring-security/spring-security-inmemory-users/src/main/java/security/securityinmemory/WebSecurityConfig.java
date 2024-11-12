package security.securityinmemory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    @Bean
    protected UserDetailsService userDetailsService() {
        List<UserDetails> list = new ArrayList<UserDetails>();

        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("123")
                        .roles("USER")
                        .build();

        UserDetails usr01 = User.withDefaultPasswordEncoder()
                .username("omid")
                .password("123")
                .roles("ADMIN")
                .build();
        list.add(usr01);
        list.add(user);

        return new InMemoryUserDetailsManager(list);
    }
}
