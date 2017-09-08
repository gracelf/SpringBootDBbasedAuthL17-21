package me.grace.springboot17;

import me.grace.springboot17.models.Role;
import me.grace.springboot17.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private SSUerDetailsService userDetailsService;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new SSUerDetailsService(userRepo);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{


        //autherization with different roles, change hasRole() into hasAuthorization()
//        http
//                .authorizeRequests()
//                .antMatchers("/")
//                .access("hasRole('ROLE_USER') or hasRole('ROLE_ADMIN')")
//                .antMatchers("/admin")
//                .access("hasRole('ROLE_ADMIN')")
//                .anyRequest().authenticated()
//                .and()
//                .formLogin().loginPage("/login").permitAll()
//                .and()
//                .httpBasic();

        http
                .authorizeRequests()
                .antMatchers("/", "/adduser", "/register","/registerAdmin", "/css/**", "/img/**", "/js/**", "/fonts/**", "/font-awesome/**").permitAll()

                //the following doesn't work for the Spring 4 use "hasAuthority"
//                .antMatchers("/admin")
//                .access("hasRole('ROLE_ADMIN')")

                // the following works, only when has role as "ADMIN", the user can see /admin route
                .antMatchers("/admin")
                .access("hasAuthority('ADMIN')")

                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll().permitAll()
                .and()
                .httpBasic();

        http
                .csrf().disable();

        http
                .headers().frameOptions().disable();

    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception
    {
        auth
                .userDetailsService(userDetailsServiceBean());
    }

//    //accept different paramater the the configure method above
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//
//        auth.inMemoryAuthentication().
//                withUser("user").password("password").roles("USER")
//
//        //to add another user, delete ";" from the above command and add
//                //roles can be customized and give difference authorization
//                //password can be encrypted later on
//        .and().withUser("dave").password("begreat").roles("ADMIN");
//
//        }

    }