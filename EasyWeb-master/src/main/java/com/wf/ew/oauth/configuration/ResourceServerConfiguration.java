package com.wf.ew.oauth.configuration;

import com.wf.ew.oauth.provider.UrlRegistryProvider;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import java.util.Map;
import java.util.Set;
import java.util.function.Consumer;

/**
 * @author wangfan
 * @since
 **/
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter implements ApplicationContextAware {
    public static final String RESOURCE_NAME = "easyweb";
    @Value("${api.version}/**")
    private String apiPrefPath;
    private ApplicationContext applicationContext;

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.resourceId(RESOURCE_NAME).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/**")
                .permitAll()
                .antMatchers(apiPrefPath)
                .authenticated()
                .and()
                .headers().frameOptions().sameOrigin()
                .and().cors()
                .and().csrf().disable();
        registerWatchdogProvider(http.authorizeRequests());
    }

    private void registerWatchdogProvider(ExpressionUrlAuthorizationConfigurer.ExpressionInterceptUrlRegistry authorizeRequests) {
        Map<String, UrlRegistryProvider> watchdogProviders = applicationContext.getBeansOfType(UrlRegistryProvider.class);
        watchdogProviders.values().forEach(new Consumer<UrlRegistryProvider>() {
            @Override
            public void accept(UrlRegistryProvider urlRegistryProvider) {
                urlRegistryProvider.configure(authorizeRequests);
            }
        });
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
