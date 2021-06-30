import React from "react";
import {motion} from "framer-motion";
import "./CreateUserPlan.css";
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
    TextField
} from "@material-ui/core";
import {toast} from "react-hot-toast";
import PlanService from "../../../services/PlanService"
import ResponseData from "../../../types/http/ResponseData";
import MyError from "../../../types/http/MyError";
import PlanModel from "../../../types/models/plan/PlanModel";
import {Link} from "react-router-dom";

class CreateUserPlan extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            isLoading: false,
            description: null,
            recipeAmount: 1,
            planDays: 3,
        }
    }

    createOptions() {
        return [1, 2, 3, 4, 5, 6, 7].map(
            (item: number) => (
                <MenuItem key={item + Math.random()} value={item}>{item}</MenuItem>
            )
        )
    }

    async createPlan(){
        this.setState({isLoading: true});
        try {
            if (this.state.description === null || this.state.description === "" || this.state.description.length < 3) {
                throw new Error("Please set the description field!")
            }
            let response: any = await PlanService.createUserPlan({
                recipeAmount: this.state.recipeAmount,
                description: this.state.description,
                planDays: this.state.planDays,
            });
            if(!response.successfulOperation){
                response = response as MyError;
                throw new Error(response.error);
            }
            response = response as ResponseData<PlanModel>;
            if(response.code === 201){
                toast.success("Created user plan successfully!");
                this.props.history.push("/plan");
            }
        }
        catch (e) {
            toast.error(e.message);
        }
        this.setState({isLoading: false});
    }

    async componentDidMount(){

    }

    render() {
        return (
            <div className={"container-custom"}>
                {
                    this.state.isLoading ?
                        <Backdrop
                            style={{
                                zIndex: 1
                            }}
                            open={this.state.isLoading}>
                            <CircularProgress
                                disableShrink
                                size={"100px"}
                            />
                        </Backdrop> : null
                }
                <motion.div
                    animate={{ y: 0, opacity: 1 }}
                    initial={{y: -100, opacity: 0}}
                    transition={{ ease: "easeOut", duration: 1 }}
                    className={"container-main shadow container-create-user-plan"}>
                    <Grid
                        container
                    >
                        <Grid
                            container
                            justify={"center"}
                        >
                            <p className={"text-title custom-title-text"}>Create plan</p>
                        </Grid>
                        <Grid
                            container
                            justify={"center"}
                        >
                            <Grid
                                item
                                xs={11}
                            >
                                <Card
                                    className={"card-custom"}
                                >
                                    <Grid
                                        container
                                        spacing={5}
                                    >
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-input"}
                                        >
                                            <FormControl
                                                variant={"filled"}
                                                className={"text-field"}
                                            >
                                                <InputLabel>
                                                    Recipe amount
                                                </InputLabel>
                                                <Select
                                                    onChange={(event)=>{
                                                        let temp = parseInt(event.target.value as string);
                                                        this.setState({recipeAmount: temp});
                                                    }}
                                                    value={this.state.recipeAmount}
                                                >
                                                    {
                                                        this.createOptions()
                                                    }
                                                </Select>
                                            </FormControl>
                                        </Grid>
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-input"}
                                        >
                                            <FormControl
                                                variant={"filled"}
                                                className={"text-field"}
                                            >
                                                <InputLabel>
                                                    Plan days
                                                </InputLabel>
                                                <Select
                                                    onChange={(event)=>{
                                                        let temp = parseInt(event.target.value as string);
                                                        this.setState({planDays: temp});
                                                    }}
                                                    value={this.state.planDays}
                                                >
                                                    {
                                                        this.createOptions()
                                                    }
                                                </Select>
                                            </FormControl>
                                        </Grid>
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-input"}
                                        >
                                            <TextField
                                                label={"Description"}
                                                variant={"filled"}
                                                onChange={(event)=>{
                                                    this.setState({description: event.target.value});
                                                }}
                                                className={"text-field"}
                                            />
                                        </Grid>
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-input"}
                                        >
                                            <Button
                                                variant="contained"
                                                color={"primary"}
                                                size={"large"}
                                                onClick={this.createPlan.bind(this)}
                                            >
                                                Create
                                            </Button>
                                        </Grid>
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-input"}
                                        >
                                            <Link
                                                to={"/plan"}
                                            >
                                                <Button
                                                    variant="contained"
                                                    color={"primary"}
                                                    size={"large"}
                                                >
                                                    Go to plan
                                                </Button>
                                            </Link>
                                        </Grid>
                                    </Grid>
                                </Card>
                            </Grid>
                        </Grid>
                    </Grid>
                </motion.div>
            </div>
        );
    }
}

export default CreateUserPlan;
