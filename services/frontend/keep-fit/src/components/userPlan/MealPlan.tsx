import React from 'react';
import './MealPlan.css';
import {
    Backdrop,
    Button, ButtonBase,
    ButtonGroup,
    Card,
    CardMedia,
    CircularProgress,
    Grid,
    Typography
} from "@material-ui/core";
import {toast} from "react-hot-toast";
import NoUserPlan from "../../exceptions/NoUserPlan";
import PlanService from "../../services/PlanService";
import ResponseData from "../../types/http/ResponseData";
import PlanModel from "../../types/models/plan/PlanModel";
import MyError from "../../types/http/MyError";
import {motion} from "framer-motion";
import {Link} from 'react-router-dom';
import MenuModel from "../../types/models/plan/MenuModel";
import ArrowRightIcon from "@material-ui/icons/ArrowRight";
import WebInfo from "../../services/WebInfo";

class MealPlan extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            isLoading: true,
            data: null,
            selectedMenu: 1
        }
    }

    renderSelectedMenu(){
        let temp: MenuModel = this.state.data.menus[this.state.selectedMenu - 1];
        return (
            <div
                style={{
                    width: "100%"
                }}
            >
                <Grid
                    container
                    spacing={5}
                >
                    <Grid
                        item
                        xs={12}
                        className={"grid-custom-center"}
                    >
                        <Typography
                            variant={"h5"}
                        >
                            {temp.day}
                        </Typography>
                    </Grid>
                    <Grid
                        container
                        spacing={6}
                    >
                        {
                            temp.meals.map((item) => (
                                <Grid
                                    item
                                    xs={12}
                                >
                                    <Grid
                                        container
                                        spacing={5}
                                    >
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-custom-center"}
                                        >
                                            <Typography variant={"h4"}>{item.timeOfDay}</Typography>
                                        </Grid>
                                        <Grid
                                            item
                                            xs={12}
                                            className={"grid-custom-center"}
                                        >
                                            {
                                                item.mealRecipe.map((item) => (
                                                    <Card
                                                        raised
                                                        className={"card-meal"}
                                                    >
                                                        <ButtonBase
                                                            onClick={ () => this.props.history.push(`/recipe/${item.recipe.id}`)}
                                                        >
                                                            <Grid
                                                                container
                                                                className={"card-presentation"}
                                                            >
                                                                <CardMedia
                                                                    src={`${WebInfo.HOST_IMAGE}${item.recipe.images[0].imagePath}`}
                                                                    title="Recipe"
                                                                    component={"img"}
                                                                    alt={"Recipe image"}
                                                                    style={{
                                                                        height: 300,
                                                                        width: 500
                                                                    }}
                                                                />
                                                                <Typography
                                                                    align={"center"}
                                                                    variant={"h4"}>{item.recipe.name}</Typography>
                                                            </Grid>
                                                            <Grid
                                                                container
                                                                spacing={4}
                                                            >
                                                                <Grid
                                                                    item
                                                                    xs={12}
                                                                >
                                                                    <Typography
                                                                        align={"center"}
                                                                        variant={"h5"}
                                                                    >
                                                                        <b>Description:</b>
                                                                    </Typography>
                                                                    <Typography
                                                                        align={"center"}
                                                                    >
                                                                        {item.recipe.description}
                                                                    </Typography>
                                                                </Grid>
                                                                <Grid
                                                                    item
                                                                    xs={12}
                                                                >
                                                                    <Typography
                                                                        align={"center"}
                                                                        variant={"h5"}
                                                                    >
                                                                        <b>Keywords:</b>
                                                                    </Typography>
                                                                    <Typography
                                                                        align={"center"}
                                                                    >
                                                                        {item.recipe.keywords}
                                                                    </Typography>
                                                                </Grid>
                                                                <Grid
                                                                    item
                                                                    xs={12}
                                                                >
                                                                    <Typography
                                                                        align={"center"}
                                                                        variant={"h5"}
                                                                    >
                                                                        <b>Categories:</b>
                                                                    </Typography>

                                                                    {
                                                                        item.recipe.categories.map((item)=>(
                                                                            <Typography
                                                                                align={"center"}
                                                                            >
                                                                                <ArrowRightIcon/>
                                                                                {item.category}
                                                                            </Typography>
                                                                        ))
                                                                    }
                                                                </Grid>
                                                            </Grid>
                                                        </ButtonBase>
                                                    </Card>
                                                ))
                                            }
                                        </Grid>
                                    </Grid>
                                </Grid>
                            ))
                        }
                    </Grid>
                </Grid>
            </div>
        );
    }

    async componentDidMount(){
        try {
            let response: any = await PlanService.getUserPlan();
            if(!response.successfulOperation){
                response = response as MyError;
                let message = response.error.split(" ").slice(1).join(" ");
                if(response.code === 404){
                    throw new NoUserPlan(message)
                }
                throw new Error(message);
            }
            response = response as ResponseData<PlanModel>;
            this.setState({data: response.data});
        }
        catch (err) {
            if(err instanceof NoUserPlan){
                this.props.history.push("/createPlan");
                toast.error(`No user plan set yet! Please create your plan.`);
            } else {
                toast.error(`Loading profile failed! ${err.message}`);
            }
        }
        this.setState({isLoading: false});
    }

    render() {
        return (
            <div className={"container-custom"}>
                {
                    !this.state.isLoading ? <motion.div
                            animate={{ y: 0, opacity: 1 }}
                            initial={{y: -100, opacity: 0}}
                            transition={{ ease: "easeOut", duration: 1 }}
                            className={"container-main shadow container-user-plan"}>
                            <Grid
                                container
                            >
                                <Grid
                                    container
                                    justify={"center"}
                                >
                                    <p className={"text-title custom-title-text"}>Plan</p>
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
                                                    className={"grid-custom-center"}
                                                >
                                                    <Typography
                                                        variant={"h6"}
                                                    >
                                                        Plan description: {this.state.data.description}
                                                    </Typography>
                                                </Grid>
                                                <Grid
                                                    item
                                                    xs={12}
                                                    className={"grid-custom-center"}
                                                >
                                                    <ButtonGroup
                                                        color={"primary"}
                                                        variant={"contained"}
                                                        size={"large"}
                                                    >
                                                        {
                                                            Array.from({length: this.state.data.planDays},
                                                                (_, index) => index + 1).map((item) => (
                                                                <Button
                                                                    onClick={() => this.setState({selectedMenu: item})}
                                                                >{item}</Button>
                                                            ))
                                                        }
                                                    </ButtonGroup>
                                                </Grid>
                                                <Grid
                                                    className={"grid-custom-center"}
                                                    item
                                                    xs={12}
                                                >
                                                    {
                                                        this.renderSelectedMenu()
                                                    }
                                                </Grid>
                                                <Grid
                                                    className={"grid-custom-center"}
                                                    item
                                                    xs={12}
                                                >
                                                    <Link
                                                        to={"/createPlan"}
                                                    >
                                                        <Button
                                                            variant="contained"
                                                            color={"primary"}
                                                            size={"large"}
                                                        >
                                                            Create a new plan
                                                        </Button>
                                                    </Link>
                                                </Grid>
                                            </Grid>
                                        </Card>
                                    </Grid>
                                </Grid>
                            </Grid>
                        </motion.div>
                        :
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

export default MealPlan;
