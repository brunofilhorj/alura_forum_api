package br.com.alura.forum.controller

import br.com.alura.forum.dto.NovoTopico
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.dto.AtualizaTopico
import br.com.alura.forum.service.TopicoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/topicos")
class TopicoController(
    private val service: TopicoService
) {

    @GetMapping
    fun listar(): List<TopicoView> {
        return service.getAll()
    }

    @GetMapping("/{id}")
    fun buscarPorId(@PathVariable id: Long): TopicoView {
        return service.getById(id)
    }

    @PostMapping
    @Transactional
    fun cadastrar(
        @RequestBody @Valid dto: NovoTopico,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<TopicoView> {
        val view = service.create(dto)
        val uri = uriBuilder.path("/topicos/${view.id}").build().toUri()
        return ResponseEntity.created(uri).body(view)
    }

    @PutMapping
    @Transactional
    fun atualizar(@RequestBody @Valid dto: AtualizaTopico): ResponseEntity<TopicoView> {
        val view = service.update(dto)
        return ResponseEntity.ok(view)
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun remover(@PathVariable id: Long) {
        service.delete(id)
    }
}