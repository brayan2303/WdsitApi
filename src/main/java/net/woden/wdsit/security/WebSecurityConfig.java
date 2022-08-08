package net.woden.wdsit.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                // Maximo de sesiones abiertas en el navegador 
                // .sessionManagement().maximumSessions(2)
                //.and()
                .cors()
                .and()
              //  .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              // .and()
                .addFilterAfter(authenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeRequests()
                //Permite la entrada de cualquier usuario, al login
                .antMatchers(HttpMethod.POST, "/genPerson/login").permitAll()
                .antMatchers(HttpMethod.GET, "/genCountry/listActive").permitAll()
                .antMatchers(HttpMethod.GET, "/certCert/ftpTIGO").permitAll()
                .antMatchers(HttpMethod.GET, "/ftpTigo/list").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportWipAutomaticS/generateWip").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportValidateLoadWfsmS/wfsm/{abreviatura}/{country}/{customer}").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportValidateLoad/listAll/{country}").permitAll() 
                .antMatchers(HttpMethod.GET, "/ReportValidateLoadCountryS/listCountry/{country}").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportValidateLoadCountryS/listCountryRetry/{country}").permitAll()
                .antMatchers(HttpMethod.GET, "/WeekCountYesNoS/getDate/{date}").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportValidateLoadWfsmS/wfsm/{abreviatura}/{country}/{customer}").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportValidateLoad/listAll/{country}").permitAll() 
                .antMatchers(HttpMethod.GET, "/ReportValidateLoadCountryS/listCountry/{country}").permitAll()
                .antMatchers(HttpMethod.GET, "/ReportValidateLoadCountryS/listCountryRetry/{country}").permitAll()
                .antMatchers(HttpMethod.POST, "/ComCommodityEntry/v1/entrada/prealerta").permitAll()
                .anyRequest().authenticated();
    }

    @Bean
    public JwtAuthorizationFilter authenticationTokenFilter() throws Exception {
        JwtAuthorizationFilter filter = new JwtAuthorizationFilter();
        return filter;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }
}