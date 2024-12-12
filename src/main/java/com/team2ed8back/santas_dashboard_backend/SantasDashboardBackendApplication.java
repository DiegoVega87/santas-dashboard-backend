package com.team2ed8back.santas_dashboard_backend;

import com.team2ed8back.santas_dashboard_backend.controller.ChristmasLetterController;
import com.team2ed8back.santas_dashboard_backend.entity.reindeer.Reindeer;
import com.team2ed8back.santas_dashboard_backend.entity.reindeerAlignment.ReindeerAlignment;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerAlignmentService;
import com.team2ed8back.santas_dashboard_backend.service.reindeer.ReindeerService;
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
		ReindeerAlignmentService reindeerAlignmentService = context.getBean(ReindeerAlignmentService.class);
		reindeerAlignmentService.insertDefaultAlignments();

	}

}
