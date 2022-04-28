package com.paymybuddy.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
//
	  public void addViewControllers(ViewControllerRegistry registry) {
		    registry.addViewController("/").setViewName("login");
		    registry.addViewController("/addUtilisateur").setViewName("addUtilisateur");
		    registry.addViewController("/addAmi").setViewName("addAmi");
		    registry.addViewController("/accueil").setViewName("accueil");
		  }
}
