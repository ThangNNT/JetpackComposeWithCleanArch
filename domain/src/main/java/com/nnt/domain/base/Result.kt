package com.nnt.domain.base

sealed class Result<out T: Any> {
    object Empty : Result<Nothing>()
    class Success <out T: Any>(val data: T?) : Result<T>()
    class Error(val errorModel: BaseModel) : Result<Nothing>()
    object Loading : Result<Nothing>()

    override fun toString(): String {
        return if(this is Success<T>){
            data.toString()
        } else if(this is Error) {
            (this).errorModel.toString()
        } else if(this is Loading) {
            "Loading"
        }
        else "Empty"
    }
}