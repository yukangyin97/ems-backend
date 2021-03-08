package ca.uottawa.config;

import ca.uottawa.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // all apis except login need authentication
        registry.addInterceptor(new JWTInterceptor())
                .addPathPatterns("/api/employees/**")
                .excludePathPatterns("/api/login");
    }
}
