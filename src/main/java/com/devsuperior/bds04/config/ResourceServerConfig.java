package com.devsuperior.bds04.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Autowired
    private Environment environment;

    @Autowired
    private JwtTokenStore tokenStore;

    private static final String [] PUBLIC = { "/oauth/token", "/h2-console/**" };

    private static final String [] OPERATOR_GET = { "/cities/**", "/events/**" };

    private static final String [] OPERATOR_POST = { "/events/**" };

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.tokenStore(tokenStore);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        disableProfileTest(http);

        http.authorizeRequests()
                .antMatchers(PUBLIC)
                .permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_GET)
                .permitAll()
                .antMatchers(HttpMethod.GET, OPERATOR_GET)
                .hasAnyRole("CLIENT", "ADMIN")
                .antMatchers(HttpMethod.POST, OPERATOR_POST)
                .hasAnyRole("CLIENT")
                .anyRequest()
                .hasAnyRole("ADMIN");
    }

    private void disableProfileTest(HttpSecurity http) throws Exception {
        if (Arrays.asList(environment.getActiveProfiles()).contains("test")) {
            http.headers().frameOptions().disable();
        }
    }
}
