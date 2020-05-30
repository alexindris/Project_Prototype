package com.tcm.prototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.tcm.prototype", "com.tcm.prototype.api", "com.tcm.prototype.application",
	"com.tcm.prototype.aplication.dto", "com.tcm.prototype.utilities"})
@EntityScan("com.tcm.prototype.domain")
@EnableJpaRepositories(basePackages="com.tcm.prototype.persistence")

public class ProjectPrototypeApiRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectPrototypeApiRestApplication.class, args);
	}

}
