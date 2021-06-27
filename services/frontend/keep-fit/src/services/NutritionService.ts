import axios from "axios";
import WebInfo from "./WebInfo";

class NutritionService{
    getRecipes(params: any = null){
        return axios.get(`http://${WebInfo.HOST}:${WebInfo.PORT}/api/backend/nutrition/recipe`,{
            params: params
        })
            .then(
                response => {
                    return response.data
                }
            )
            .catch(
                error => {
                    if (error.response){
                        return error.response.data;
                    }
                    console.log(error);
                }
            );
    }
}

const nutritionService = new NutritionService();
export default nutritionService;
