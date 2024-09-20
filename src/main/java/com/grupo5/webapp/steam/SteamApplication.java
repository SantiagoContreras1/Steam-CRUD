package com.grupo5.webapp.steam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.grupo5.webapp.steam.system.Main;

import javafx.application.Application;

@SpringBootApplication
public class SteamApplication {

	public static void main(String[] args) {
		Application.launch(Main.class, args);
		SpringApplication.run(SteamApplication.class, args);
	}

}
