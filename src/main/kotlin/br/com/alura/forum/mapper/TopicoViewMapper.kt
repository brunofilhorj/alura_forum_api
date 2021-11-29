package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicoView
import br.com.alura.forum.model.Topico
import org.springframework.stereotype.Component

@Component
class TopicoViewMapper: IMapper<Topico, TopicoView> {

    override fun map(source: Topico): TopicoView {
        return TopicoView(
            id = source.id,
            titulo = source.titulo,
            mensagem = source.mensagem,
            dataCriacao = source.dataCriacao,
            status = source.status
        )
    }

}