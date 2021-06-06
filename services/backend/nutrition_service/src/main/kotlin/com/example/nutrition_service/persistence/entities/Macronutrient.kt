package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.MacronutrientModel
import com.example.nutrition_service.persistence.tables.Macronutrients
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Macronutrient(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Macronutrient>(Macronutrients)
    var carbohydrates   by Macronutrients.carbohydrates
    var proteins        by Macronutrients.proteins
    var fats            by Macronutrients.fats
    var saturatedFats   by Macronutrients.saturatedFats
    var fibers          by Macronutrients.fibers
    var sugars          by Macronutrients.sugars
}

fun Macronutrient.toMacronutrientModel(): MacronutrientModel {
    return MacronutrientModel(
        id = this.id.value,
        carbohydrates = this.carbohydrates,
        proteins = this.proteins,
        fats = this.fats,
        saturatedFats = this.saturatedFats,
        fibers = this.fibers,
        sugars = this.sugars
    )
}