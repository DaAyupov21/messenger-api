package ru.damirayupov.messenger.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import ru.damirayupov.messenger.api.components.AccountValidityInterceptor;

public class AppConfig implements WebMvcConfigurer {

    @Autowired
    private AccountValidityInterceptor accountValidityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(accountValidityInterceptor);
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
