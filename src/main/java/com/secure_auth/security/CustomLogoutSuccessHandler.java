package com.secure_auth.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CustomLogoutSuccessHandler {

    private final ClientRegistrationRepository clientRegistrationRepository;

    public OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
                new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("https://login.microsoftonline.com/common/oauth2/v2.0/logout");

        return oidcLogoutSuccessHandler;
    }

}
