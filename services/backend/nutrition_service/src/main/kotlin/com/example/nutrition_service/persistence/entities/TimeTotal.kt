package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.TimeTotalModel
import com.example.nutrition_service.persistence.tables.TimeTotals
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class TimeTotal(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<TimeTotal>(TimeTotals)
    var prepTime by TimeTotals.prepTime
    var cookTime by TimeTotals.cookTime
    var totalTime by TimeTotals.totalTime
}

fun TimeTotal.toTimeTotalModel(): TimeTotalModel{
    return TimeTotalModel(
        id = this.id.value,
        prepTime = this.prepTime ?: "",
        cookTime = this.cookTime ?: "",
        totalTime = this.totalTime ?: ""
    )
}