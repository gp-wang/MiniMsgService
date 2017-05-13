package challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Autowired
    DataSource datasource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        /*auth
                .inMemoryAuthentication()
                .withUser("Jack London").password("").roles("USER");*/

        auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery("select name,'' from person where name = ? ");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/h2-console/*").permitAll()
                //.antMatchers("/*").permitAll()
                .anyRequest().authenticated()
                .and().httpBasic();

        http.csrf().disable();
        http.headers().frameOptions().disable();

    }
}