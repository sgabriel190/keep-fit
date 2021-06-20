package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.GenderModel
import com.example.nutrition_service.persistence.tables.Genders
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Gender(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Gender>(Genders)
    var name by Genders.name
}

fun Gender.toGenderModel() = GenderModel(
    id = id.value,
    name = this.name
)