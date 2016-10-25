package se.torgammelgard.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@ComponentScan(basePackages = "se.torgammelgard.config")
@EnableWebMvc
public class WebConfig extends WebMvcConfigurerAdapter {

}
