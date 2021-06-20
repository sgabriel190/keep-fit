package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.DietTypeModel
import com.example.nutrition_service.persistence.tables.DietTypes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class DietType(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<DietType>(DietTypes)
    var name by DietTypes.name
    var calories by DietTypes.calories
}

fun DietType.toDietTypeModel() = DietTypeModel(
    id = id.value,
    name = this.name,
    calories = this.calories
)