package xin.shaonianyou.forum.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import xin.shaonianyou.forum.config.intercepors.LoginIntercepor;

@Configuration
public class MyWebMvcConfigurerAdapter implements WebMvcConfigurer {


    //配置资源映射路径
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/**");
        registry.addResourceHandler("/avators/**").addResourceLocations("file:D:/ZW/Workspace/upload/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration registration = registry.addInterceptor(new LoginIntercepor());
        registration.addPathPatterns("/admin/**");
        registration.excludePathPatterns("/admin/login");
    }


}
