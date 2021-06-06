package com.example.nutrition_service.persistence.entities

import com.example.nutrition_service.persistence.pojos.ImageModel
import com.example.nutrition_service.persistence.tables.Images
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class Image(id: EntityID<Int>): IntEntity(id) {
    companion object: IntEntityClass<Image>(Images)
    var imagePath   by Images.imagePath
    var idRecipe    by Images.idRecipe
}

fun Image.toImageModel(): ImageModel{
    return ImageModel(
        id = this.id.value,
        imagePath = this.imagePath,
        idRecipe = this.idRecipe.value
    )
}