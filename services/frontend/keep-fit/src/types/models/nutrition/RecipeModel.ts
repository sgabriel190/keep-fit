import TimeTotalModel from "./TimeTotalModel";
import CategoryModel from "./CategoryModel";
import ImagesModel from "./ImagesModel";
import InstructionModel from "./InstructionModel";
import IngredientModel from "./IngredientModel";
import NutrientModel from "./NutrientModel";

type RecipeModel = {
    id: number;
    timeTotal: TimeTotalModel;
    name: string;
    description: string;
    keywords: string;
    categories: CategoryModel[];
    images: ImagesModel[];
    instructions: InstructionModel[];
    ingredients: IngredientModel[];
    nutrients: NutrientModel;
};

export default RecipeModel;
