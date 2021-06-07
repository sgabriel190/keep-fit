package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.CategoryModel
import com.example.nutrition_service.persistence.tables.Categories
import com.example.nutrition_service.persistence.tables.RecipeToCategories
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Category(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Category>(Categories)
    var category    by Categories.category
}

fun Category.toCategoryModel(): CategoryModel{
    return CategoryModel(
        id = this.id.value,
        category = this.category
    )
}