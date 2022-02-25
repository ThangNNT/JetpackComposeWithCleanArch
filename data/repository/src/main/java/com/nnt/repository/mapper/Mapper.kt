package com.nnt.repository.mapper

abstract class Mapper<I,O> {
    abstract fun map(input: I): O
    fun mapList(input: List<I>?): List<O>?{
        return input?.map{
            map(it)
        }
    }
    fun mapOrNull(input: I?): O? {
        return if(input!=null){
            map(input)
        }
        else null
    }
}