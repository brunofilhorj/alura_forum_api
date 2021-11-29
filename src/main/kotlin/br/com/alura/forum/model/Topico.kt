package br.com.alura.forum.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
data class Topico(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var titulo: String,
    var mensagem: String,
    val dataCriacao: LocalDateTime = LocalDateTime.now(),
    @ManyToOne
    val curso: Curso,
    @ManyToOne
    var autor: Usuario,
    @Enumerated(value = EnumType.STRING)
    var status: StatusTopico = StatusTopico.NAO_RESPONDIDO,
    @OneToMany(mappedBy = "topico")
    val respostas: List<Resposta> = ArrayList()
)