package br.com.cwi.reset.primeiroprojetospring.controller;

import static br.com.cwi.reset.primeiroprojetospring.domain.Genero.MASCULINO;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cwi.reset.primeiroprojetospring.domain.Diretor;
import br.com.cwi.reset.primeiroprojetospring.domain.Filme;
import br.com.cwi.reset.primeiroprojetospring.domain.Genero;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    @GetMapping
    public Filme get() {
        Filme filme = new Filme();

        filme.setNome("As Branquelas");
        filme.setAnoLancamento(2010);
        filme.setAvaliacao(10.0);
        filme.setDescricao("É legal demais");
        filme.setDuracao(120);
        filme.setDiretor(new Diretor("Gerundio", LocalDate.now(), 20, MASCULINO));

        return filme;
    }
}
