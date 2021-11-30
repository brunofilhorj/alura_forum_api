package br.com.alura.forum.service

import br.com.alura.forum.dto.AtualizaTopico
import br.com.alura.forum.dto.NovoTopico
import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.NovoTopicoMapper
import br.com.alura.forum.mapper.TopicoViewMapper
import br.com.alura.forum.repository.TopicoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.util.*
import java.util.stream.Collectors


@Service
class TopicoService(
    private val repository: TopicoRepository,
    private val novoTopicoMapper: NovoTopicoMapper,
    private val topicoViewMapper: TopicoViewMapper,
    private val notFoundMessage: String = "Tópico não encontrado"
) {

    fun getAll(
        nomeCurso: String?,
        paginacao: Pageable
    ): Page<TopicoView> {
        /*val uuid = UUID.randomUUID()
        println("UUID         = $uuid")
        println("UUID version = ${uuid.version()}")
        println("UUID variant = ${uuid.variant()}")*/

        val topicos = if (nomeCurso == null) {
            repository.findAll(paginacao)
        } else {
            repository.findByCursoNome(nomeCurso, paginacao)
        }

        return topicos.map { t -> topicoViewMapper.map(t) }
    }

    fun getById(id: Long): TopicoView {
        val t = repository.findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }

        return topicoViewMapper.map(t)
    }

    fun create(dto: NovoTopico): TopicoView {
        val topico = novoTopicoMapper.map(dto)
        repository.save(topico)
        return topicoViewMapper.map(topico)
    }

    fun update(dto: AtualizaTopico): TopicoView {
        val topico = repository.findById(dto.id)
            .orElseThrow { NotFoundException(notFoundMessage) }

        topico.titulo = dto.titulo
        topico.mensagem = dto.mensagem

        return topicoViewMapper.map(topico)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}