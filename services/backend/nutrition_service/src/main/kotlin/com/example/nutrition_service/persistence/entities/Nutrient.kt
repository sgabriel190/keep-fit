package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.NutrientModel
import com.example.nutrition_service.persistence.tables.Nutrients
import com.example.nutrition_service.persistence.tables.Recipes
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Nutrient(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Nutrient>(Nutrients)
    var calories            by Nutrients.calories
    var macronutrients      by Macronutrient referencedOn Nutrients.idMacronutrients
}

fun Nutrient.toNutrientModel(): NutrientModel {
    return NutrientModel(
        calories = this.calories,
        macronutrients = this.macronutrients.toMacronutrientModel()
    )
}