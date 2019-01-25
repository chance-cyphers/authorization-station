package com.pants.chance.oathserver

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer

@Configuration
class OAuth2Configuration : AuthorizationServerConfigurerAdapter() {

    @Autowired
    private val authManager: AuthenticationManager? = null
    @Autowired
    private val userDetailsService: UserDetailsService? = null

    @Throws(Exception::class)
    override fun configure(clients: ClientDetailsServiceConfigurer?) {
        clients!!.inMemory()
                .withClient("race_place")
                .secret("{noop}secreterthanyou")
                .authorizedGrantTypes("refresh_token", "password", "client_credentials")
                .scopes("mobileclient")
    }

    override fun configure(endpoints: AuthorizationServerEndpointsConfigurer?) {
        endpoints!!.authenticationManager(authManager)
                .userDetailsService(userDetailsService)
    }

}