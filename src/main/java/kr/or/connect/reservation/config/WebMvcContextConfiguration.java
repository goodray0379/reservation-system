package kr.or.connect.reservation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "kr.or.connect.reservation.controller" })
public class WebMvcContextConfiguration extends WebMvcConfigurerAdapter {
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/META-INF/resources/webjars/").setCachePeriod(31556926);
        registry.addResourceHandler("/css/**").addResourceLocations("/reservation-html-base/css/").setCachePeriod(31556926);
        registry.addResourceHandler("/img/**").addResourceLocations("/reservation-html-base/img/").setCachePeriod(31556926);
        registry.addResourceHandler("/js/**").addResourceLocations("/reservation-html-base/js/").setCachePeriod(31556926);
        registry.addResourceHandler("/font/**").addResourceLocations("/reservation-html-base/font/").setCachePeriod(31556926);
    }
 
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
   
    @Override
    public void addViewControllers(final ViewControllerRegistry registry) {
    		registry.addViewController("/").setViewName("mainpage.jsp");
            registry.addViewController("/detail").setViewName("detail.jsp");
            registry.addViewController("/myreservation").setViewName("myreservation.jsp");
            registry.addViewController("/reserve").setViewName("reserve.jsp");
            registry.addViewController("/bookinglogin").setViewName("bookinglogin.jsp");
            registry.addViewController("/review").setViewName("review.html");
            registry.addViewController("/reviewWrite").setViewName("reviewWrite.html");
    }
    
    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/reservation-html-base/htmls/");
        resolver.setSuffix("");
        return resolver;
    }
}