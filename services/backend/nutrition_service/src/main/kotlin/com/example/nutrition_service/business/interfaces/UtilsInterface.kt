package com.example.nutrition_service.business.interfaces

interface UtilsInterface {

    fun computeIdealWeight(height: Int, age: Int, gender: Int): Int
    fun computeBMI(weight: Int, height: Int): Float
    fun computeWND(idealWeight: Int): Int
    fun computeCalories(idealWeight: Int, dietTypeIndex: Int, activityTypeIndex: Float): Int
}