package com.schwarz.onlinelibrary.configuration;

import com.schwarz.onlinelibrary.security.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class TokenInterceptorConfigurer implements WebMvcConfigurer {
   @Autowired
   private TokenInterceptor tokenInterceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(tokenInterceptor);
   }
}