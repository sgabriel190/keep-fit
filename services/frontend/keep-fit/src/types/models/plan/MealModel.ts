import MealRecipeModel from "./MealRecipeModel";

type MealModel = {
    timeOfDay: string;
    mealRecipe: MealRecipeModel[];
}

export default MealModel;
