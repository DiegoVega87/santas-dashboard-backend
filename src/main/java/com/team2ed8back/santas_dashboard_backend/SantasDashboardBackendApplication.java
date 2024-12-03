package com.team2ed8back.santas_dashboard_backend;

import com.team2ed8back.santas_dashboard_backend.controller.AddresController;
import com.team2ed8back.santas_dashboard_backend.controller.ChristmasLetterController;
import com.team2ed8back.santas_dashboard_backend.service.childs.ChildService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SantasDashboardBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SantasDashboardBackendApplication.class, args);
		ChristmasLetterController christmasLetterController = context.getBean(ChristmasLetterController.class);
		christmasLetterController.insertChristmasLetters();
		context.getBean(ChildService.class).saveChildList();
		AddresController addresController = context.getBean(AddresController.class);
	}

//	@Configuration
//	public static class Myconfiguration{
//		@Bean
//		public WebMvcConfigurer corsConfigurer(){
//			return new WebMvcConfigurer() {
//				@Override
//				public void addCorsMappings(CorsRegistry registry) {
//					registry.addMapping("/**")
//							.allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
//				}
//			};
//		}
//	}

}
