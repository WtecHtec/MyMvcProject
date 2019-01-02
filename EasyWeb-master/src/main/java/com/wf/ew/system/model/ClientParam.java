package com.wf.ew.system.model;

import java.util.HashSet;
import java.util.Set;

/**
 * 客户端Client接口参数封装
 * Created by wangfan on 2018-12-11 下午 3:50.
 */
public class ClientParam {
    public final static String DEFAULT_REDIRECT_URL = "urn:ietf:wg:oauth:2.0:oob";
    @org.codehaus.jackson.annotate.JsonProperty("client_name")
    @com.fasterxml.jackson.annotation.JsonProperty("client_name")
    private String clientName;
    private Set<String> scope;
    @org.codehaus.jackson.annotate.JsonProperty("redirect_uri")
    @com.fasterxml.jackson.annotation.JsonProperty("redirect_uri")
    private Set<String> redirectUri;

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public Set<String> getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(Set<String> redirectUri) {
        this.redirectUri = redirectUri;
    }

    public Set<String> getScope() {
        return scope;
    }

    public void setScope(Set<String> scope) {
        this.scope = scope;
    }

    public void populateDefault() {
        if (getScope() == null) {
            setScope(new HashSet<>());
        }
        if (getRedirectUri() == null) {
            setRedirectUri(new HashSet<>());
        }
        if (this.getRedirectUri().size() == 0) {
            getRedirectUri().add(DEFAULT_REDIRECT_URL);
        }
        if (getScope().size() == 0) {
            getScope().add("DEFAULT");
        }
    }
}
