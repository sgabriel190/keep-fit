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
    var idealWeight by UserDetails.idealWeight
    var calories by UserDetails.calories
    var bmi by UserDetails.bmi
    var wnd by UserDetails.wnd
    var idActivityType by ActivityType referencedOn UserDetails.idActivityType
    var idGender by Gender referencedOn UserDetails.idGender
    var idDietType by DietType referencedOn UserDetails.idDietType
}

fun UserDetail.toUserDetailModel() = UserDetailModel(
    age = this.age,
    height = this.height,
    weight = this.weight,
    idealWeight = this.idealWeight,
    calories = this.calories,
    bmi = this.bmi,
    wnd = this.wnd,
    activityType = this.idActivityType.toActivityTypeModel(),
    gender = this.idGender.toGenderModel(),
    dietType = this.idDietType.toDietTypeModel()
)
