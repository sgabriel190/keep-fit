import React from "react";
import {motion} from "framer-motion";
import "./UserDetails.css";
import {Link} from 'react-router-dom';
import {
    Backdrop,
    Button,
    Card,
    CircularProgress,
    FormControl,
    Grid,
    InputLabel,
    MenuItem,
    Select,
    TextField,
} from "@material-ui/core";
import ResponseData from "../../types/http/ResponseData";
import NutritionService from "../../services/NutritionService";
import MyError from "../../types/http/MyError";
import {toast} from "react-hot-toast";
import GenderModel from "../../types/models/nutrition/GenderModel";
import ActivityTypeModel from "../../types/models/nutrition/ActivityTypeModel";
import DietTypeModel from "../../types/models/nutrition/DietTypeModel";
import UserDetailsModel from "../../types/models/nutrition/UserDetailsModel";
import _ from "lodash";
import NoUserDetails from "../../exceptions/NoUserDetails";
import statusBmi from "../../helpers/StatusBmi";

class UserDetails extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            userDetails: null,
            userDetailsAux: null,
            genders: null,
            activityTypes: null,
            dietTypes: null,
            isLoading: true,
            exists: true
        };

    }

    difference(object: object, base: object) {
        function changes(object: object, base: object) {
            return _.transform(object, function(result: object, value: any, key: any) {
                // @ts-ignore
                if (!_.isEqual(value, base[key])) {
                    // @ts-ignore
                    result[key] = (_.isObject(value) && _.isObject(base[key])) ? changes(value, base[key]) : value;
                }
            });
        }
        return changes(object, base);
    }

    async sendUserDetailsUpdate(){
        try {
            if (!_.isEqual(this.state.userDetails, this.state.userDetailsAux) && this.state.exists) {
                let difference: any = this.difference(this.state.userDetailsAux, this.state.userDetails);
                if ("dietType" in difference) {
                    difference.idDietType = difference["dietType"].id;
                    delete difference.dietType;
                }
                if ("activityType" in difference) {
                    difference.idActivityType = difference["activityType"].id;
                    delete difference.activityType;
                }
                if ("gender" in difference) {
                    difference.idGender = difference["gender"].id;
                    delete difference.gender;
                }
                const response: ResponseData<any> = await NutritionService.patchUserDetails(difference);
                this.checkResponse(response);
                this.setState({userDetails: response.data});
                this.setState({userDetailsAux: response.data});
                toast.success(`Updated user details!`);
                console.log(this.state.userDetails);
            }
            else {
                let data = {
                    age: this.state.userDetailsAux.age,
                    height: this.state.userDetailsAux.height,
                    weight: this.state.userDetailsAux.weight,
                    idGender: this.state.userDetailsAux.gender.id,
                    idActivityType: this.state.userDetailsAux.activityType.id,
                    idDietType: this.state.userDetailsAux.dietType.id,
                }
                const response: ResponseData<any> = await NutritionService.postUserDetails(data);
                let responseUserDetails: ResponseData<any> = await NutritionService.getUserDetails();
                this.checkResponse(response);
                this.checkResponse(responseUserDetails);
                this.setState({userDetails: responseUserDetails.data});
                this.setState({userDetailsAux: responseUserDetails.data});
                toast.success(`Created user details!`);
                console.log(this.state.userDetails);
            }
        }
        catch (e) {
            toast.error(`Updating user details failed!\n${e}`);
        }
    }

    checkResponse(response: ResponseData<any>){
        if (!response.successfulOperation){
            response = response as ResponseData<MyError>;
            let message = response.error.split(" ").slice(1).join(" ");
            if(response.code === 404){
                throw new NoUserDetails(message);
            }
            throw new Error(message);
        }
    }

    async componentDidMount(){
        try {
            let responseGenders: ResponseData<any> = await NutritionService.getGenders();
            this.checkResponse(responseGenders);
            let responseActivityTypes: ResponseData<any> = await NutritionService.getActivityType();
            this.checkResponse(responseActivityTypes);
            let responseDietTypes: ResponseData<any> = await NutritionService.getDietTypes();
            this.checkResponse(responseDietTypes);
            responseGenders = responseGenders as ResponseData<GenderModel[]>;
            responseActivityTypes = responseActivityTypes as ResponseData<ActivityTypeModel[]>;
            responseDietTypes = responseDietTypes as ResponseData<DietTypeModel[]>;
            this.setState({genders: responseGenders.data});
            this.setState({activityTypes: responseActivityTypes.data});
            this.setState({dietTypes: responseDietTypes.data});

            let responseUserDetails: ResponseData<any> = await NutritionService.getUserDetails();
            this.checkResponse(responseUserDetails);
            responseUserDetails = responseUserDetails as ResponseData<UserDetailsModel>;
            this.setState({userDetails: responseUserDetails.data});
            this.setState({userDetailsAux: Object.assign({}, responseUserDetails.data)});
            this.setState({isLoading: false});
        }
        catch (e) {
            if(e instanceof NoUserDetails){
                let tmp: UserDetailsModel = {
                    age: 0,
                    height: 0,
                    weight: 0,
                    idealWeight: 0,
                    calories: 0,
                    bmi: 0,
                    wnd: 0,
                    activityType: {
                        id: 1,
                        name: "",
                        calories: 0
                    },
                    gender: {
                        id: 1,
                        name: ""
                    },
                    dietType: {
                        id: 1,
                        name: "",
                        calories: 0
                    }
                }
                this.setState({exists: false});
                this.setState({userDetails: tmp});
                this.setState({userDetailsAux: Object.assign({}, tmp)});
                toast.error(`No user details set yet! Please set your user details first.`);
            } else {
                toast.error(`Loading user details failed!\n${e.message}`)
            }
            this.setState({isLoading: false});
        }
    }

    render() {
        return (
            <div className={"container-custom"}>
                {
                    !this.state.isLoading ?
                        <motion.div
                            animate={{ y: 0, opacity: 1 }}
                            initial={{y: -100, opacity: 0}}
                            transition={{ ease: "easeOut", duration: 1 }}
                            className={"container-main container-user-details shadow"}
                        >
                            <Grid
                                container
                            >
                                <Grid
                                    container
                                    justify={"center"}
                                >
                                    <p className={"text-title custom-title-text"}>User details</p>
                                </Grid>
                                <Grid
                                    container
                                    justify={"center"}
                                >
                                    <Grid
                                        item
                                    >
                                        <Card
                                            className={"card-custom"}
                                        >
                                            <Grid
                                                container
                                                spacing={5}
                                            >
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="Age"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        onChange={(event) =>
                                                        {
                                                            let temp = this.state.userDetailsAux;
                                                            temp.age = parseInt(event.target.value);
                                                            this.setState({userDetailsAux: temp});
                                                        }}
                                                        defaultValue={this.state.userDetails.age}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="Height (cm)"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        onChange={(event) =>
                                                        {
                                                            let temp = this.state.userDetailsAux;
                                                            temp.height = parseInt(event.target.value);
                                                            this.setState({userDetailsAux: temp});
                                                        }}
                                                        defaultValue={this.state.userDetails.height}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="Weight (kg)"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        onChange={(event) =>
                                                        {
                                                            let temp = this.state.userDetailsAux;
                                                            temp.weight = parseInt(event.target.value);
                                                            this.setState({userDetailsAux: temp});
                                                        }}
                                                        defaultValue={this.state.userDetails.weight}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="Ideal weight (kg)"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        disabled={true}
                                                        value={this.state.userDetails.idealWeight}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="Calories (kcal)"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        disabled={true}
                                                        value={this.state.userDetails.calories}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="BMI"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        disabled={true}
                                                        value={this.state.userDetails.bmi + " " + statusBmi(this.state.userDetails.bmi)}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <TextField
                                                        label="Water need per day (ml)"
                                                        variant="filled"
                                                        className={"text-field"}
                                                        disabled={true}
                                                        value={this.state.userDetails.wnd}
                                                    />
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <FormControl
                                                        className={"text-field"}
                                                    >
                                                        <InputLabel>Activity type</InputLabel>
                                                        <Select
                                                            variant="filled"
                                                            defaultValue={this.state.userDetails.activityType.id ? this.state.userDetails.activityType.id : 1}
                                                            onChange={(event) =>
                                                            {
                                                                let id = parseInt(event.target.value as string);
                                                                let temp = this.state.userDetailsAux;
                                                                temp.activityType = this.state.activityTypes.filter((value: ActivityTypeModel) => {
                                                                    return value.id === id;
                                                                })[0];
                                                                this.setState({userDetailsAux: temp});
                                                            }}
                                                        >
                                                            {
                                                                this.state.activityTypes.map(
                                                                    (item: ActivityTypeModel) => (
                                                                        <MenuItem
                                                                            key={Math.random()}
                                                                            value={item.id}>{item.name}</MenuItem>
                                                                    )
                                                                )
                                                            }
                                                        </Select>
                                                    </FormControl>
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <FormControl
                                                        className={"text-field"}
                                                    >
                                                        <InputLabel>Gender</InputLabel>
                                                        <Select
                                                            variant="filled"
                                                            defaultValue={this.state.userDetails.gender.id ? this.state.userDetails.gender.id : 1}
                                                            onChange={(event) =>
                                                            {
                                                                let id = parseInt(event.target.value as string);
                                                                let temp = this.state.userDetailsAux;
                                                                temp.gender = this.state.genders.filter((value: GenderModel) => {
                                                                    return value.id === id;
                                                                })[0];
                                                                this.setState({userDetailsAux: temp});
                                                            }}
                                                        >
                                                            {
                                                                this.state.genders.map(
                                                                    (item: GenderModel) => (
                                                                        <MenuItem
                                                                            key={Math.random()}
                                                                            value={item.id}>{item.name}</MenuItem>
                                                                    )
                                                                )
                                                            }
                                                        </Select>
                                                    </FormControl>
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={6}>
                                                    <FormControl
                                                        className={"text-field"}
                                                    >
                                                        <InputLabel>Diet type</InputLabel>
                                                        <Select
                                                            variant="filled"
                                                            defaultValue={this.state.userDetails.dietType.id ? this.state.userDetails.dietType.id : 1}
                                                            onChange={(event) =>
                                                            {
                                                                let id = parseInt(event.target.value as string);
                                                                let temp = this.state.userDetailsAux;
                                                                temp.dietType = this.state.dietTypes.filter((value: DietTypeModel) => {
                                                                    return value.id === id;
                                                                })[0];
                                                                this.setState({userDetailsAux: temp});
                                                            }}
                                                        >
                                                            {
                                                                this.state.dietTypes.map(
                                                                    (item: DietTypeModel) => (
                                                                        <MenuItem
                                                                            key={Math.random()}
                                                                            value={item.id}>{item.name}</MenuItem>
                                                                    )
                                                                )
                                                            }
                                                        </Select>
                                                    </FormControl>
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={12}>
                                                    <Button
                                                        variant="contained"
                                                        color="primary"
                                                        onClick={this.sendUserDetailsUpdate.bind(this)}
                                                        size={"large"}
                                                    >
                                                        Update user details
                                                    </Button>
                                                </Grid>
                                                <Grid item className={"grid-input"} xs={12}>
                                                    <Link
                                                        to={"/profile"}
                                                    >
                                                        <Grid item className={"grid-input"} xs={12}>
                                                            <Button
                                                                variant="contained"
                                                                color="primary"
                                                                size={"large"}
                                                            >
                                                                Go to user profile
                                                            </Button>
                                                        </Grid>
                                                    </Link>
                                                </Grid>
                                            </Grid>
                                        </Card>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </motion.div> :
                        <Backdrop  open={this.state.isLoading}>
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

export default UserDetails;
