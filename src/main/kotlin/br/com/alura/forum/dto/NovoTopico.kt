package br.com.alura.forum.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive
import javax.validation.constraints.Size

data class NovoTopico(
    @field:NotEmpty(message = "Título é obrigatório") @field:Size(min = 5, max = 100, message = "Título deve ser entre 5 e 100 caracteres") val titulo: String,
    @field:NotEmpty(message = "Mensagem é obrigatória") @field:Size(min = 5, max = 1000, message = "Título deve ser entre 5 e 1000 caracteres") val mensagem: String,
    @field:NotNull @field:Positive val idCurso: Long,
    @field:NotNull @field:Positive var idAutor: Long
)