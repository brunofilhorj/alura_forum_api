package br.com.alura.forum.mapper

import br.com.alura.forum.dto.NovoTopico
import br.com.alura.forum.model.Topico
import br.com.alura.forum.service.CursoService
import br.com.alura.forum.service.UsuarioService
import org.springframework.stereotype.Component

@Component
class NovoTopicoMapper(
    private val cursoService: CursoService,
    private val usuarioService: UsuarioService
) : IMapper<NovoTopico, Topico> {

    override fun map(source: NovoTopico): Topico {
        return Topico(
            titulo = source.titulo,
            mensagem = source.mensagem,
            curso = cursoService.getById(source.idCurso),
            autor = usuarioService.getById(source.idAutor)
        )
    }

}