package kr.gyk.voyageventures.beautyq.lite.web.service.config;


import kr.gyk.voyageventures.beautyq.lite.web.service.component.LogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LogInterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/css/**", "/error");
    }
}
