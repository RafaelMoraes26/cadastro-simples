package br.com.cadastro.simples;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class CadastroSimplesApplication {

	public static void main(String[] args) {
		SpringApplication.run(CadastroSimplesApplication.class, args);
	}

}
