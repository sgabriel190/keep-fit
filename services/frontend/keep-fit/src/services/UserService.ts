import LoggerService from "./LoggerService";
import WebInfo from "./WebInfo";
import axios from "axios";

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
                console.log(error);
            });
    }

    getUser(){
        LoggerService.formatLog(this.constructor.name.toString(), "getUser()")
        return axios.get(
            `http://${WebInfo.HOST}:${WebInfo.PORT}/api/backend/users/user`
        ).then(
            (response) => {
                return response.data;
            })
            .catch((error) => {
                console.log(error);
            });
    }
}

const userService = new UserService();
export default userService;
