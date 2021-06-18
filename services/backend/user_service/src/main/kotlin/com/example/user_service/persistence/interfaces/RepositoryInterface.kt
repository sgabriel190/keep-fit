package com.example.user_service.persistence.interfaces


interface RepositoryInterface<T, K> {
    fun getById(id: Int): T?
    fun updateById(id: Int, data:T): T?
    fun insertData(data: T)
    fun deleteById(id: Int)
}