package com.wf.ew.oauth.provider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;

/**
 * Created by wangfan on 2018-12-11 下午 3:26.
 */
public interface UrlRegistryProvider {
    /**
     * 配置权限入口
     *
     * @param config
     * @return added result
     */
    boolean configure(ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry config);
}
