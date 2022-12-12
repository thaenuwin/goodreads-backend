/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.goodreadsbackend.api;


import com.goodreadsbackend.api.util.CommonLogger;
import com.goodreadsbackend.api.util.JsonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.common.DefaultExpiringOAuth2RefreshToken;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.security.KeyPair;
import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author thaenuwin
 */

@Configuration
public class TokenConfig {
    
    private static final Class TAG=TokenConfig.class;
    
    @Autowired
    private KeyPairProvider keyPairProvider;

    
    @Bean
    public JwtAccessTokenConverter accessTokenConverter(){
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
            

            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                final Map<String,Object> additionalInfo = new HashMap<>();
                Map<String,String> params = authentication.getOAuth2Request().getRequestParameters();
                
                String principalJsonString= JsonUtil.toJsonString(authentication.getPrincipal());
                CommonLogger.log(TAG, "========= Principal ==========");
                CommonLogger.log(TAG, principalJsonString);
                CommonLogger.log(TAG, "==============================");
                
                CommonLogger.log(TAG, "========= Permission ==========");
                CommonLogger.log(TAG, "==============================");
                
                additionalInfo.putAll(params);
                additionalInfo.put("randUuid", UUID.randomUUID().toString().replace("-", ""));
                
                DefaultOAuth2AccessToken accToken=((DefaultOAuth2AccessToken)accessToken);
                
                accToken.setAdditionalInformation(additionalInfo);                

                long now=System.currentTimeMillis();
                accToken=((DefaultOAuth2AccessToken)accessToken);
                
                //1 hour to expire
                accToken.setExpiration(new Date(now+(1000*60*3)));
                
                //2 hours to expire
                accToken.setRefreshToken(new DefaultExpiringOAuth2RefreshToken(UUID.randomUUID().toString(),new Date(now+(1000*60*5))));
                
                
                return super.enhance(accessToken, authentication); 
            }
            
            
        };
        
        
        
        KeyPair keyPair=keyPairProvider.fetchKeystore();
        converter.setKeyPair(keyPair);
        
        return converter;
    }
    
    @Bean
    public TokenStore tokenStore(){
        return new JwtTokenStore(accessTokenConverter());
    }
    
    @Bean
    @Primary
    public DefaultTokenServices tokenServices(){
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        defaultTokenServices.setTokenEnhancer(accessTokenConverter());
        return defaultTokenServices;
    }
}
