package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.InstructionModel
import com.example.nutrition_service.persistence.tables.Instructions
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Instruction(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Instruction>(Instructions)
    var instruction by Instructions.instruction
    var idRecipe    by Instructions.idRecipe
}

fun Instruction.toInstructionModel(): InstructionModel{
    return InstructionModel(
        instruction = this.instruction
    )
}