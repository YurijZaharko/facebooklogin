package com.example.facebooklogin.service;


import com.example.facebooklogin.facebook.social.conectionFactory.FacebookConnectionFactoryV30;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
@PropertySource("/static/properties/prop.properties")
public class AuthFlowServiceImpl {


    private Environment environment;

    public String createFacebookAuthorizationURL(){
        FacebookConnectionFactoryV30 connectionFactory = new FacebookConnectionFactoryV30(environment.getProperty("prop.facebook.clientId"),
                environment.getProperty("prop.facebook.clientSecret"));
        OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
        OAuth2Parameters params = new OAuth2Parameters();
        params.setRedirectUri("https://localhost:8443/test");
        params.setScope("public_profile,email,user_birthday");
        final String s = oauthOperations.buildAuthorizeUrl(params);
        return s;

    }



    public void createFacebookAccessToken(String code) {
        FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(environment.getProperty("prop.facebook.clientId"),
                environment.getProperty("prop.facebook.clientSecret"));
        AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, "https://localhost:8443/test", null);
        Facebook facebook = new FacebookTemplate(accessGrant.getAccessToken());
        System.out.println();
    }

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
