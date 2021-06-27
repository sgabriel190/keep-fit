import TimeTotalModel from "./TimeTotalModel";
import CategoryModel from "./CategoryModel";

type RecipeLiteModel = {
    id: number;
    timeTotal: TimeTotalModel;
    name: string;
    description: string;
    keywords: string;
    categories: CategoryModel[];
};

export default RecipeLiteModel;
