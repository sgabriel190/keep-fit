import React from "react";
import UserService from "../../services/UserService";
import ResponseData from "../../types/http/ResponseData";
import './Profile.css';
import '../../shared/styles/shared.css';
import MyError from "../../types/http/MyError";
import {toast} from "react-hot-toast";
import UserProfileModel from "../../types/models/UserProfileModel";
import {motion} from "framer-motion";
import Button from "@material-ui/core/Button";
import {Backdrop, CircularProgress, Grid, Paper} from "@material-ui/core";
import {Link} from 'react-router-dom';


class Profile extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            userData: null,
            ready: false,
        };
    }

    async componentDidMount(){
        try {
            let response: ResponseData<any> = await UserService.getUserProfile();
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                let message = response.error.split(" ").slice(1).join(" ");
                throw new Error(message);
            }
            response = response as ResponseData<UserProfileModel>;
            this.setState({userData: response.data});
            this.setState({ready: true});
        }
        catch (err) {
            toast.error(`Loading profile failed! ${err.message}`);
        }
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
                                            BMI: {this.state.userData.userDetails.bmi}
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
                                            BMI: {this.state.userData.userDetails.bmi}
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
                                </Grid>
                            </div>
                            <Grid className={"margin-y"} container spacing={2} >
                                <Grid item xs={12}>
                                    <Grid container justify={"center"} spacing={3}>
                                        <Grid item>
                                            <Link
                                                to={"/user/details"}
                                            >
                                                <Button
                                                    variant="contained"
                                                    color={"primary"}>Update user details</Button>
                                            </Link>
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
