import TimeTotalModel from "./TimeTotalModel";
import CategoryModel from "./CategoryModel";
import ImagesModel from "./ImagesModel";

type RecipeLiteModel = {
    id: number;
    timeTotal: TimeTotalModel;
    name: string;
    description: string;
    keywords: string;
    categories: CategoryModel[];
    images: ImagesModel[];
};

export default RecipeLiteModel;
