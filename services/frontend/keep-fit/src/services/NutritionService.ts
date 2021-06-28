import WebInfo from "./WebInfo";

class NutritionService{
    getRecipes(params: any = null){
        return WebInfo.httpClient.get(
            `/nutrition/recipe`,{
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

    getRecipe(id: number){
        return WebInfo.httpClient.get(
            `/nutrition/recipe/${id}`)
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

    getCategories(){
        return WebInfo.httpClient.get(
            `/nutrition/recipe/category`)
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
