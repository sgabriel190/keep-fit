import LoggerService from "./LoggerService";
import WebInfo from "./WebInfo";
import axios from "axios";
import store from "../helpers/store";
import removeJwt from "../helpers/actionLogout";

class UserService {

    login(data: object){
        LoggerService.formatLog(this.constructor.name.toString(), "login")
        return axios.post(
            `http://${WebInfo.HOST}:${WebInfo.PORT}/api/backend/users/login`,
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
        LoggerService.formatLog(this.constructor.name.toString(), "register()")
        return axios.put(
            `http://${WebInfo.HOST}:${WebInfo.PORT}/api/backend/users/register`,
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
        LoggerService.formatLog(this.constructor.name.toString(), "getUser()")
        return axios.get(
            `http://${WebInfo.HOST}:${WebInfo.PORT}/api/backend/users/user`
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
    logout(){
        sessionStorage.removeItem("jwt");
        store.dispatch(removeJwt);
    }
}

const userService = new UserService();
export default userService;
