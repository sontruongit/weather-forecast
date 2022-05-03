package com.nab.weatherforecast.utils.mappers

interface Mapper<I, O> {
    fun map(input: I): O
}

interface ListMapper<I, O> : Mapper<List<I>, List<O>>

class ListMapperImpl<I, O>(private val mapper: Mapper<I, O>) : ListMapper<I, O> {

    override fun map(input: List<I>): List<O> {
        return input.map { mapper.map(it) }
    }
}