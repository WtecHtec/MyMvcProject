package com.wf.ew.oauth.configuration;

import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.AliasFor;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * Created by wangfan on 2018-12-11 下午 3:55.
 */
@Retention(value = java.lang.annotation.RetentionPolicy.RUNTIME)
@Target(value = {java.lang.annotation.ElementType.TYPE})
@Documented
@Import({ResourceServerConfiguration.class, AuthorizationServerConfiguration.class})
@EnableWebSecurity
public @interface EnableOAuthClient {

    @AliasFor(annotation = EnableWebSecurity.class)
    boolean debug() default false;
}
