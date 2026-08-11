package id.putra.wms.config;

import com.vaadin.flow.spring.security.VaadinWebSecurity;

import id.putra.wms.views.login.LoginView;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // http.authorizeHttpRequests(auth -> auth
        //         .requestMatchers(
        //                 "/images/**",
        //                 "/line-awesome/**",
        //                 "/error/**",
        //                 "/swagger-ui.html",
        //                 "/swagger-ui/**",
        //                 "/v3/api-docs/**"
        //         ).permitAll()
        // );
        super.configure(http);
        setLoginView(http, LoginView.class); 
    }

   

}