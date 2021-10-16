package br.com.cwi.reset.primeiroprojetospring.controller;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.util.Objects.nonNull;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.cwi.reset.primeiroprojetospring.domain.Filme;

@RestController
@RequestMapping("/filme")
public class FilmeController {

    private static List<Filme> filmes = new ArrayList<>();

    @PostMapping
    public ResponseEntity<Filme> save(@RequestBody final Filme entity) {
        final Optional<Filme> filme = buscarFilmePorNome(entity.getNome());

        if (filme.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        filmes.add(entity);

        return ok(entity);
    }

    @GetMapping
    public List<Filme> get() {
        return filmes;
    }

    @GetMapping("/{nome}")
    public ResponseEntity<Filme> getByName(@PathVariable("nome") final String nome) {
        final Filme filme = buscarFilmePorNome(nome).orElseGet(null);

        return nonNull(filme) ? ok(filme) : notFound().build();
    }

    @DeleteMapping("/{nome}")
    public ResponseEntity<Void> delete(@PathVariable("nome") final String nome) {
        if (findAndRemove(nome)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Filme> update (@RequestBody final Filme entity) {
        if (findAndRemove(entity.getNome())) {
            return ResponseEntity.notFound().build();
        }

        filmes.add(entity);

        return ok(entity);
    }

    private Optional<Filme> buscarFilmePorNome(final String nome) {
        return filmes.stream().filter(filme -> nome.equals(filme.getNome())).findFirst();
    }

    private boolean findAndRemove(@PathVariable("nome") final String nome) {
        final Optional<Filme> filme = buscarFilmePorNome(nome);

        if (!filme.isPresent()) {
            return TRUE;
        }

        filmes.remove(filme.get());

        return FALSE;
    }

}
