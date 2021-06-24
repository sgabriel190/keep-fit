import React from "react";
import UserService from "../../services/UserService";
import ResponseData from "../../types/models/ResponseData";

class Profile extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            username: null,
            password: null
        };
    }

    async componentDidMount(){
        let user: ResponseData<any> = await UserService.getUser();
        console.log(user);
    }
    render() {
        return(
            <h1>Profile page works!</h1>
        );
    }
}

export default Profile;
