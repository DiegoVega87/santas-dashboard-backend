package com.team2ed8back.santas_dashboard_backend;

import com.team2ed8back.santas_dashboard_backend.controller.ChristmasLetterController;
import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerService;
import com.team2ed8back.santas_dashboard_backend.service.childs.ChildService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;


@SpringBootApplication
public class SantasDashboardBackendApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SantasDashboardBackendApplication.class, args);
		ChristmasLetterController christmasLetterController = context.getBean(ChristmasLetterController.class);
		christmasLetterController.insertChristmasLetters();
		ReindeerService reindeerService = context.getBean(ReindeerService.class);
		initializeReindeers(reindeerService);
		context.getBean(ChildService.class).saveChildList();
	}

	public static void initializeReindeers(ReindeerService reindeerService) {
		reindeerService.saveAllReindeers(List.of(
				new Reindeer("Dasher", "Fast"),
				new Reindeer("Dancer", "Fast"),
				new Reindeer("Prancer", "Strong"),
				new Reindeer("Vixen", "Strong"),
				new Reindeer("Comet", "Fast"),
				new Reindeer("Cupid", "Strong"),
				new Reindeer("Donner", "Strong"),
				new Reindeer("Blitzen", "Strong"),
				new Reindeer("Rudolph", "Leader")
		));
	}
}
