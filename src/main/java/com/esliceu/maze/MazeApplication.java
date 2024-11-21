package com.esliceu.maze;

import com.esliceu.maze.filters.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class MazeApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(MazeApplication.class, args);
	}

	@Autowired
	LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(
				loginInterceptor
		).addPathPatterns("/start", "/map", "nav");
	}
}
