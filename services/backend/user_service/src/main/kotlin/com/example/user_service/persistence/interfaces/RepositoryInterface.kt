package com.example.user_service.persistence.interfaces


interface RepositoryInterface<T, K> {
    fun getById(id: Int): T
    fun updateById(id: Int): T
    fun insertData(data: K): T
    fun deleteById(id: Int): T
}