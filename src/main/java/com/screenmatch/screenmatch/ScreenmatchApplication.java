package com.screenmatch.screenmatch;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.screenmatch.screenmatch.model.DadosEpisodio;
import com.screenmatch.screenmatch.model.DadosSerie;
import com.screenmatch.screenmatch.model.DadosTemporada;
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
		ConverteDados converteDados = new ConverteDados();

		String jsonSerie = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=6585022c");
		DadosSerie dadosSerie = converteDados.obterDados(jsonSerie, DadosSerie.class);
		System.out.println(dadosSerie);

		String jsonEpisodio = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=1&episode=2&apikey=6585022c");
		DadosEpisodio dadosEpisodio = converteDados.obterDados(jsonEpisodio, DadosEpisodio.class);
		System.out.println(dadosEpisodio);

		List<DadosTemporada> temporadas = new ArrayList<>();
		for (int i = 1; i <= dadosSerie.totalTemporadas(); i++) {
			String jsonTemporeda = consumoApi.obterDados("https://www.omdbapi.com/?t=gilmore+girls&season=" + i + "&apikey=6585022c");
			DadosTemporada dadosTemporada = converteDados.obterDados(jsonTemporeda, DadosTemporada.class);
			temporadas.add(dadosTemporada);
		}
		temporadas.forEach(System.out::println);
		
		// json = consumoApi.obterDados("https://coffee.alexflipnote.dev/random.json");
		// System.out.println(json);
	}
}
