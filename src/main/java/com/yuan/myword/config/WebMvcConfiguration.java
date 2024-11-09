package com.yuan.myword.config;

import com.yuan.myword.interceptor.JwtTokenInterceptor;
import com.yuan.myword.security.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableMethodSecurity
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private JwtTokenInterceptor jwtTokenInterceptor;

    @Value("${login.gitee.clientId}")
    private String giteeClientId;

    @Value("${login.gitee.clientSecret}")
    private String giteeClientSecret;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login/**", "/register");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173")
                //.allowedOriginPatterns("*")  //更改为部署后地址
                .allowedMethods("*")
                .allowCredentials(true)
                .allowedHeaders("token", "Authorization", "Content-Type")
                .maxAge(3600);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.securityMatcher("/login/*")
                .authorizeHttpRequests(authorize -> authorize.anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                        .usernameParameter("username")
                        .passwordParameter("123456")
                        .failureUrl("/login?error")
                        .successHandler(new MyAuthenticationSuccessHandler())
                        .failureHandler(new MyAuthenticationFailureHandler())
                )
                .logout(logout -> logout
                        .logoutSuccessHandler(new MyLogoutSuccessHandler())
                )
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(new MyAuthenticationEntryPoint())
                        .accessDeniedHandler(new MyAccessDeniedHandler())
                )
                .cors(withDefaults())
                .sessionManagement(session -> session
                        .maximumSessions(1)
                        .expiredSessionStrategy(new MySessionInformationExpiredStrategy())
                )
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/login")
                        .permitAll()
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=oauth2")
                        .successHandler(new MyOAuth2AuthenticationSuccessHandler())
                        .failureHandler(new MyOAuth2AuthenticationFailureHandler())
                );

        return http.build();
    }
    @Bean
    public ClientRegistration giteeClientRegistration() {
        return ClientRegistration.withRegistrationId("gitee")
                .clientId(giteeClientId)
                .clientSecret(giteeClientSecret)
                .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .redirectUri("{baseUrl}/login/oauth2/code/{registrationId}")
                .authorizationUri("https://gitee.com/oauth/authorize")
                .tokenUri("https://gitee.com/oauth/token")
                .userInfoUri("https://gitee.com/api/v5/user")
                .userNameAttributeName("login")
                .scope("user_info")
                .build();
    }
}
