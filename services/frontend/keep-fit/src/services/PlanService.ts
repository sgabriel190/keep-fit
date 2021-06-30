import WebInfo from "./WebInfo";

class PlanService{
    getUserPlan(){
        return WebInfo.httpClient.get("/planning/plan/user")
            .then(
                response => {
                    return response.data;
                }
            )
            .catch(
                error => {
                    if(error.response){
                        return error.response.data;
                    }
                    console.log(error);
                }
            )
    }

    createUserPlan(data: object){
        return WebInfo.httpClient.post("/planning/plan/user", data)
            .then(
                response => {
                    return response.data;
                }
            )
            .catch(
                error => {
                    if(error.response){
                        return error.response.data;
                    }
                    console.log(error);
                }
            )
    }
}

const planService = new PlanService();
export default planService;
