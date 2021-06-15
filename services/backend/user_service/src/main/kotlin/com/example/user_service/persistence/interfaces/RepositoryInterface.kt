package com.example.user_service.persistence.interfaces


interface RepositoryInterface<T, K> {
    fun getById(id: Int): T?
    fun updateById(id: Int, data:K ): T?
    fun insertData(data: K)
    fun deleteById(id: Int)
}