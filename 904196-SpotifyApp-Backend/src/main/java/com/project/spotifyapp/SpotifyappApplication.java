package com.project.spotifyapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.project.spotifyapp.filter.AppFilter;

@SpringBootApplication
public class SpotifyappApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpotifyappApplication.class, args);
	}

	// create appfilter bean for security
	@Bean
	public AppFilter appFilter() {
		return new AppFilter();
	}

}
