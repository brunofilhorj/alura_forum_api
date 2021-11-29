package br.com.alura.forum.mapper

interface IMapper<T, U> {
    fun map(source: T): U
}