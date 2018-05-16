package com.example.facebooklogin.facebook.social.conectionFactory;

import com.example.facebooklogin.facebook.social.provider.FacebookServiceV30Provider;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.connect.FacebookAdapter;

public class FacebookConnectionFactoryV30 extends OAuth2ConnectionFactory<Facebook> {

    public FacebookConnectionFactoryV30(String appId, String appSecret) {
        this(appId, appSecret, null);
    }

    public FacebookConnectionFactoryV30(String appId, String appSecret, String appNamespace) {
        super("Facebook", new FacebookServiceV30Provider(appId, appSecret, appNamespace), new FacebookAdapter());
    }
}
