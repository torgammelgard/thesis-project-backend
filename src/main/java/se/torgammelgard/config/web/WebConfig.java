package se.torgammelgard.config.web;


import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewInterceptor;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.springsecurity4.dialect.SpringSecurityDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import se.torgammelgard.formatters.TeamFormatter;
import se.torgammelgard.formatters.TennisSetScoreFormatter;

/**
 * Customization of the Spring MVC configuration. The WebConfig extends an adapter,
 * which is an implementation of WebMvcConfigurer with empty methods, which
 * allows overriding only methods we are interested in.
 */
@Configuration
@ComponentScan({"se.torgammelgard.web", "se.torgammelgard.api"})
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter implements ServletContextAware {

    private ServletContext servletContext;

    @Bean
    public TeamFormatter teamFormatter() {
        return new TeamFormatter();
    }

    @Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    	
    	//converters.add(new MappingJackson2HttpMessageConverter()); // Not needed? maybe default
		super.configureMessageConverters(converters);
	}

	@Bean
    public TennisSetScoreFormatter tennisSetScoreFormatter() {return new TennisSetScoreFormatter();}

    @Bean
    public WebRequestInterceptor openEntityManagerInViewInterceptor() {
        return new OpenEntityManagerInViewInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addWebRequestInterceptor(openEntityManagerInViewInterceptor());
        super.addInterceptors(registry);
    }
	
    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {

        registry.addFormatter(teamFormatter());
        registry.addFormatter(tennisSetScoreFormatter());
    }

    @Bean
    @Description("Thymeleaf View Resolver")
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setOrder(1);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html;charset=UTF-8");
        return resolver;
    }
    
    @Bean
    public SpringSecurityDialect dialect() {
    	return new SpringSecurityDialect();
    }
    
    @Bean
    @Description("Thymeleaf Template Engine")
    public TemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setEnableSpringELCompiler(true);
        templateEngine.addDialect(dialect());
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    @Description("Thymeleaf Template Resolver")
    public ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
        templateResolver.setPrefix("/WEB-INF/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
        registry.addResourceHandler("/images/**")
                .addResourceLocations("/resources/images/");
        registry.addResourceHandler("/styles/**")
                .addResourceLocations("/resources/styles/");
        registry.addResourceHandler("/scripts/**")
                .addResourceLocations("/resources/scripts/");
    }
}
