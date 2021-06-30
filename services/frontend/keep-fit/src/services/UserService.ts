import WebInfo from "./WebInfo";
import store from "../helpers/store";
import removeJwt from "../helpers/actionLogout";

class UserService {

    login(data: object){
        return WebInfo.httpClient.post(
            `/users/login`,
            data
            ).then(
                (response) => {
                    return response.data;
                })
            .catch((error) => {
                if(error.response){
                    return error.response.data;
                }
                console.log(error);
            });
    }

    register(data: object){
        return WebInfo.httpClient.put(
            `/users/register`,
            data
        ).then(
            (response) => {
                return response.data;
            })
            .catch((error) => {
                if(error.response){
                    return error.response.data;
                }
                console.log(error);
            });
    }

    getUserProfile(){
        return WebInfo.httpClient.get(
            `/users/user`
        ).then(
            (response) => {
                return response.data;
            })
            .catch((error) => {
                if(error.response){
                    return error.response.data;
                }
                return error;
            });
    }

    deleteUserProfile(){
        return WebInfo.httpClient.delete(
            `/users/user`
        ).then(
            (response) => {
                return response;
            })
            .catch((error) => {
                if(error.response){
                    return error.response.data;
                }
                return error;
            });
    }

    logout(){
        sessionStorage.removeItem("jwt");
        store.dispatch(removeJwt);
    }
}

const userService = new UserService();
export default userService;
