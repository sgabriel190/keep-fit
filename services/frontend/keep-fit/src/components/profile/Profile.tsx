import React from "react";
import UserService from "../../services/UserService";
import ResponseData from "../../types/http/ResponseData";
import './Profile.css';
import '../../shared/styles/shared.css';
import MyError from "../../types/http/MyError";
import {toast} from "react-hot-toast";
import UserProfileModel from "../../types/models/UserProfileModel";


class Profile extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            userData: null,
            ready: false
        };
    }

    async componentDidMount(){
        try {
            let response: ResponseData<any> = await UserService.getUserProfile();
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                throw new Error(response.error);
            }
            response = response as ResponseData<UserProfileModel>;
            this.setState({userData: response.data});
            this.setState({ready: true});
        }
        catch (e) {
            toast.error(`Login failed!\n${e}`)
        }
    }

    render() {
        return(
            <div className={"container-custom"}>
                {
                    this.state.ready ?
                        <div className={"container-main shadow container-profile"}>
                            <div className={"container-title"}>
                                <p className={"text-title custom-title-text"}>User profile</p>
                            </div>
                            <div className={"container-content"}>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Username:</div>
                                    <div className={"text-60"}>{this.state.userData.username}</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Email:</div>
                                    <div className={"text-60"}>{this.state.userData.email}</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Age:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.age}</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>BMI:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.bmi}</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Calories:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.calories} kcal</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Height:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.height /100} m</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Ideal weight:</div>
                                    <div className={"text-60 "}>{this.state.userData.userDetails.idealWeight} kg</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Weight</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.weight} kg</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>WND:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.wnd} ml</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Gender:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.gender.name}</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Activity type:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.activityType.name}</div>
                                </div>
                                <div className={"container-data"}>
                                    <div className={"text-40"}>Diet type:</div>
                                    <div className={"text-60"}>{this.state.userData.userDetails.dietType.name}</div>
                                </div>
                                <div className={"container-buttons"}>
                                    buttons
                                </div>
                            </div>
                        </div> : null
                }
            </div>
        );
    }
}

export default Profile;
