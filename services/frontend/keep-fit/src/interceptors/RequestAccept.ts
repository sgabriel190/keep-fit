import selectJwtValue from "../helpers/selector";
import {AxiosRequestConfig} from "axios";

const requestAccept = (request: AxiosRequestConfig) => {
    if (request.url?.includes("login") || request.url?.includes("register")){
        return request;
    }
    if(selectJwtValue() !== null){
        request.headers["Authorization"] = `Bearer ${selectJwtValue()}`;
    }
    return request;
};

export default requestAccept;
