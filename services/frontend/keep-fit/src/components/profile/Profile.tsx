import React from "react";
import UserService from "../../services/UserService";
import ResponseData from "../../types/http/ResponseData";
import './Profile.css';
import '../../shared/styles/shared.css';
import MyError from "../../types/http/MyError";
import {toast} from "react-hot-toast";
import UserProfileModel from "../../types/models/user/UserProfileModel";
import {motion} from "framer-motion";
import Button from "@material-ui/core/Button";
import {Backdrop, CircularProgress, Grid, Paper} from "@material-ui/core";
import {Link} from 'react-router-dom';
import NoUserDetails from "../../exceptions/NoUserDetails";
import statusBmi from "../../helpers/StatusBmi";
import {AxiosResponse} from "axios";


class Profile extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            userData: null,
            ready: false,
        };
    }

    async removeUser(){
        try {
            let response: AxiosResponse = await UserService.deleteUserProfile();
            if (response.status === 204) {
                toast.success("User removed!");
                UserService.logout();
                this.props.history.push("/login");
            } else {
                throw new Error();
            }
        }
        catch (e) {
            toast.error("Can't remove user.");
        }
    }

    async componentDidMount(){
        try {
            let response: any = await UserService.getUserProfile();
            if (!response.successfulOperation){
                response = response as MyError;
                let message = response.error.split(" ").slice(1).join(" ");
                if(response.code === 404){
                    throw new NoUserDetails(message);
                }
                throw new Error(message);
            }
            response = response as ResponseData<UserProfileModel>;
            this.setState({userData: response.data});
        }
        catch (err) {
            if(err instanceof NoUserDetails){
                this.props.history.push("/user/details");
                toast.error(`No user details set yet! Please set your user details first.`);
            } else {
                toast.error(`Loading profile failed! ${err.message}`);
            }
        }
        this.setState({ready: true});
    }

    render() {
        return(
            <div className={"container-custom"}>
                {
                    this.state.ready ?
                        <motion.div
                            animate={{ y: 0, opacity: 1 }}
                            initial={{y: -100, opacity: 0}}
                            transition={{ ease: "easeOut", duration: 1 }}
                            className={"container-main shadow container-profile"}>
                            <div className={"container-title"}>
                                <p className={"text-title custom-title-text"}>User profile</p>
                            </div>
                            <div className={"container-content"}>
                                <Grid
                                    className={"grid-margin"}
                                    container
                                    direction="row"
                                    justify="space-evenly"
                                    alignItems="center">
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Username: {this.state.userData.username}
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Email: {this.state.userData.email}
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Age: {this.state.userData.userDetails.age}
                                        </Paper>
                                    </Grid>
                                </Grid>
                                <Grid
                                    className={"grid-margin"}
                                    container
                                    direction="row"
                                    justify="space-evenly"
                                    alignItems="center">
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            BMI: {this.state.userData.userDetails.bmi + " " + statusBmi(this.state.userData.userDetails.bmi)}
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Calories: {this.state.userData.userDetails.calories} kcal
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Height: {this.state.userData.userDetails.height /100} m
                                        </Paper>
                                    </Grid>
                                </Grid>
                                <Grid
                                    className={"grid-margin"}
                                    container
                                    direction="row"
                                    justify="space-evenly"
                                    alignItems="center">
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Diet type: {this.state.userData.userDetails.dietType.name}
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Ideal weight: {this.state.userData.userDetails.idealWeight} kg
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Weight: {this.state.userData.userDetails.weight} kg
                                        </Paper>
                                    </Grid>
                                </Grid>
                                <Grid
                                    className={"grid-margin"}
                                    container
                                    direction="row"
                                    justify="space-evenly"
                                    alignItems="center">
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Water need per day: {this.state.userData.userDetails.wnd} ml
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Gender: {this.state.userData.userDetails.gender.name}
                                        </Paper>
                                    </Grid>
                                    <Grid item xs={3}>
                                        <Paper className={"paper-text"} elevation={3}>
                                            Activity type: {this.state.userData.userDetails.activityType.name}
                                        </Paper>
                                    </Grid>
                                </Grid>
                            </div>
                            <Grid className={"margin-y"} container spacing={5} >
                                <Grid item xs={12}>
                                    <Grid container justify={"center"} spacing={3}>
                                        <Grid item>
                                            <Link
                                                to={"/user/details"}
                                            >
                                                <Button
                                                    variant="contained"
                                                    size={"large"}
                                                    color={"primary"}>Update user details</Button>
                                            </Link>
                                        </Grid>
                                    </Grid>
                                </Grid>
                                <Grid item xs={12}>
                                    <Grid container justify={"center"} spacing={3}>
                                        <Grid item>
                                            <Button
                                                variant="contained"
                                                onClick={()=>{
                                                    this.removeUser().then( r => {});
                                                }}
                                                size={"large"}
                                                color={"primary"}>Delete user</Button>

                                        </Grid>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </motion.div> :
                        <Backdrop  open={this.state.ready}>
                            <CircularProgress
                                disableShrink
                                size={"100px"}
                            />
                        </Backdrop>
                }
            </div>
        );
    }
}

export default Profile;
