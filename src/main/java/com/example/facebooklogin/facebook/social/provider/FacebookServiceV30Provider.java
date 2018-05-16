package com.example.facebooklogin.facebook.social.provider;

import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

public class FacebookServiceV30Provider extends AbstractOAuth2ServiceProvider<Facebook> {
    private String appNamespace;
    private static final String API_VERSION = "3.0";
    private static final String GRAPH_API_URL = "https://graph.facebook.com/v3.0/";

    public FacebookServiceV30Provider(String appId, String appSecret, String appNamespace) {
        super(getOAuth2Template(appId, appSecret));
        this.appNamespace = appNamespace;
    }

    private static OAuth2Template getOAuth2Template(String appId, String appSecret) {
        OAuth2Template oAuth2Template = new OAuth2Template(appId, appSecret, "https://www.facebook.com/v3.0/dialog/oauth", "https://graph.facebook.com/v3.0/oauth/access_token");
        oAuth2Template.setUseParametersForClientAuthentication(true);
        return oAuth2Template;
    }

    @Override
    public Facebook getApi(String accessToken) {
        return new FacebookTemplate(accessToken, this.appNamespace);
    }
}
