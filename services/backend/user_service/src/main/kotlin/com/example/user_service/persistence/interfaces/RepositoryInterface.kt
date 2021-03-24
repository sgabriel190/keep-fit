package com.example.user_service.persistence.interfaces


interface RepositoryInterface<T, K> {
    fun getById(id: Int): T
    fun updateById(id: Int): T
    fun insertData(data: K): Boolean
    fun deleteById(id: Int): Boolean
}