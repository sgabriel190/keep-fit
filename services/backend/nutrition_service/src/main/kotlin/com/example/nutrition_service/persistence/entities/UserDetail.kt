package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.UserDetailModel
import com.example.nutrition_service.persistence.tables.UserDetails
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class UserDetail(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<UserDetail>(UserDetails)
    var age by UserDetails.age
    var height by UserDetails.height
    var weight by UserDetails.weight
    var calories by UserDetails.calories
    var bmi by UserDetails.bmi
    var idActivityType by ActivityType referencedOn UserDetails.idActivityType
}

fun UserDetail.toUserDetailModel(): UserDetailModel{
    return UserDetailModel(
        age = this.age,
        height = this.height,
        weight = this.weight,
        calories = this.calories,
        bmi = this.bmi,
        idActivityType = this.idActivityType.toActivityTypeModel()
    )
}