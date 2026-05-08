package com.ita.rpsbd;

import org.springframework.boot.SpringApplication;

public class TestRpsbdApplication {

	public static void main(String[] args) {
		SpringApplication.from(RpsbdApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
