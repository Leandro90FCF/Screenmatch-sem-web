package com.screenmatch.screenmatch;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.screenmatch.screenmatch.model.DadosSerie;
import com.screenmatch.screenmatch.service.ConsumoApi;
import com.screenmatch.screenmatch.service.ConverteDados;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ConsumoApi consumoApi = new ConsumoApi();
		String json = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		ConverteDados converteDados = new ConverteDados();
		DadosSerie dadosSerie = converteDados.obterDados(json, DadosSerie.class);

		//System.out.println(json);
		System.out.println(dadosSerie);
		
		// json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		// System.out.println(json);
	}
}
