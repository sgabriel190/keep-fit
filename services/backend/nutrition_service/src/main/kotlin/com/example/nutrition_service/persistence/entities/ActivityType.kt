package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.ActivityTypeModel
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import com.example.nutrition_service.persistence.tables.ActivityTypes

class ActivityType(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<ActivityType>(ActivityTypes)
    var name by ActivityTypes.name
}

fun ActivityType.toActivityTypeModel(): ActivityTypeModel{
    return ActivityTypeModel(
        name = this.name
    )
}