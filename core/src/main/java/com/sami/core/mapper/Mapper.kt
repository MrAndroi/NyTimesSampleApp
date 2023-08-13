package com.sami.core.mapper

abstract class Mapper<S, D> {

    abstract fun map(source: S): D

    fun mapList(source: List<S>): List<D> {
        return source.map(this::map)
    }

}