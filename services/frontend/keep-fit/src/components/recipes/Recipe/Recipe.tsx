import React, {Component} from "react";
import ResponseData from "../../../types/http/ResponseData";
import NutritionService from "../../../services/NutritionService";
import MyError from "../../../types/http/MyError";
import {toast} from "react-hot-toast";
import {
    Accordion, AccordionDetails,
    AccordionSummary,
    Backdrop,
    Card,
    CardMedia,
    CircularProgress,
    Divider,
    Grid,
    Typography
} from "@material-ui/core";
import {motion} from "framer-motion";
import './Recipe.css';
import RecipeModel from "../../../types/models/RecipeModel";
import CategoryModel from "../../../types/models/CategoryModel";
import IngredientModel from "../../../types/models/IngredientModel";
import ArrowRightIcon from '@material-ui/icons/ArrowRight';
import ExpandMoreIcon from '@material-ui/icons/ExpandMore';
import InstructionModel from "../../../types/models/InstructionModel";

class Recipe extends Component<any, any>{

    imageSrc: string = "http://localhost:2025/api/backend/nutrition/image/";

    constructor(props: object) {
        super(props);
        this.state = {
            data: null,
            id: this.props.match.params.recipeId,
            isLoading: true,
        };
    }

    async componentDidMount(){
        try {
            let response: ResponseData<any> = await NutritionService.getRecipe(this.state.id);
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                throw new Error(response.error.split(" ").slice(1).join(" "));
            }
            response = response as ResponseData<RecipeModel>;
            this.setState({data: response.data});
            this.setState({isLoading: false});
        }
        catch (e) {
            toast.error(`Loading recipe ${this.state.id} failed!\n${e}`)
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
                            className={"container-main shadow container-recipe container-size"}>
                            <Card
                                className={"card-size"}
                            >
                                <Grid
                                    container
                                    spacing={5}
                                >
                                    <Grid
                                        item
                                        xs={6}
                                        className={"image-size"}
                                    >
                                        <CardMedia
                                            src={`${this.imageSrc}${this.state.data.images[0].imagePath}`}
                                            title={"Title"}
                                            component={"img"}
                                            alt={"Recipe image"}
                                            style={{
                                                display: "block",
                                                height: "40vw"
                                            }}
                                        />
                                    </Grid>
                                    <Grid
                                        item
                                        xs={6}
                                        justify={"space-evenly"}
                                        direction={"column"}
                                        className={"flex-grid"}
                                    >
                                        <Typography
                                            variant={"h3"}
                                            align={"center"}
                                        >
                                            {this.state.data.name}
                                        </Typography>
                                        <Divider />
                                        <Typography
                                            variant={"subtitle1"}
                                            align={"center"}
                                        >
                                            <Typography
                                                variant={"subtitle2"}
                                            >
                                                Description
                                            </Typography>
                                            {this.state.data.description}
                                        </Typography>
                                        <Divider />
                                        <Typography
                                            variant={"subtitle1"}
                                            align={"center"}
                                        >
                                            <Typography
                                                variant={"subtitle2"}
                                            >
                                                Keywords
                                            </Typography>
                                            {this.state.data.keywords}
                                        </Typography>
                                        <Divider />
                                        <Typography
                                            variant={"subtitle1"}
                                            align={"center"}
                                        >
                                            <Typography
                                                variant={"subtitle2"}
                                            >
                                                Time
                                            </Typography>
                                            <div>
                                                <b>Preparation time</b>: {this.state.data.timeTotal.prepTime}
                                            </div>
                                            <div>
                                                <b>Cook time</b>: {this.state.data.timeTotal.cookTime}
                                            </div>
                                            <div>
                                                <b>Total time</b>: {this.state.data.timeTotal.totalTime}
                                            </div>
                                        </Typography>
                                        <Divider />
                                        <Typography
                                            variant={"subtitle1"}
                                            align={"center"}
                                        >
                                            <Typography
                                                variant={"subtitle2"}
                                            >
                                                Nutrients per 100g
                                            </Typography>
                                            <div>
                                                <b>Calories</b>: {this.state.data.nutrients.calories} kcal
                                            </div>
                                            <div>
                                                <b>Carbohydrates</b>: {this.state.data.nutrients.macronutrients.carbohydrates}
                                            </div>
                                            <div>
                                                <b>Fats</b>: {this.state.data.nutrients.macronutrients.fats}
                                            </div>
                                            <div>
                                                <b>Proteins</b>: {this.state.data.nutrients.macronutrients.proteins}
                                            </div>
                                            <div>
                                                <b>Saturated fats</b>: {this.state.data.nutrients.macronutrients.saturatedFats}
                                            </div>
                                            <div>
                                                <b>Fibers</b>: {this.state.data.nutrients.macronutrients.fibers}
                                            </div>
                                            <div>
                                                <b>Sugars</b>: {this.state.data.nutrients.macronutrients.sugars}
                                            </div>
                                        </Typography>

                                    </Grid>
                                    <Grid
                                        item
                                        xs={6}
                                    >
                                        <Accordion>
                                            <AccordionSummary
                                                expandIcon={<ExpandMoreIcon />}
                                            >
                                                <Typography
                                                    align={"center"}
                                                    variant={"h4"}
                                                >
                                                    Categories:
                                                </Typography>
                                            </AccordionSummary>
                                            <AccordionDetails>
                                                <Grid
                                                    container
                                                    justify={"space-evenly"}
                                                    direction={"column"}
                                                    className={"flex-grid"}
                                                >
                                                    {
                                                        this.state.data.categories.map(
                                                            (item: CategoryModel, index: number) => (
                                                                <Typography
                                                                    variant={"h6"}
                                                                    key={index * 200}
                                                                >
                                                                    <ArrowRightIcon
                                                                        fontSize={"large"}
                                                                    />
                                                                    {item.category}
                                                                </Typography>
                                                            )
                                                        )
                                                    }
                                                </Grid>
                                            </AccordionDetails>
                                        </Accordion>
                                    </Grid>
                                    <Grid
                                        item
                                        xs={6}
                                    >
                                        <Accordion>
                                            <AccordionSummary
                                                expandIcon={<ExpandMoreIcon />}
                                            >
                                                <Typography
                                                    variant={"h4"}
                                                >
                                                    Ingredients:
                                                </Typography>
                                            </AccordionSummary>
                                            <AccordionDetails>
                                                <Grid
                                                    container
                                                    justify={"space-evenly"}
                                                    direction={"column"}
                                                    className={"flex-grid"}
                                                >
                                                    {
                                                        this.state.data.ingredients.map(
                                                            (item: IngredientModel, index: number) => (
                                                                <Typography
                                                                    variant={"h6"}
                                                                    key={index * 100}
                                                                >
                                                                    <ArrowRightIcon
                                                                        fontSize={"large"}
                                                                    />
                                                                    {item.ingredient}
                                                                </Typography>
                                                            )
                                                        )
                                                    }
                                                </Grid>
                                            </AccordionDetails>
                                        </Accordion>
                                    </Grid>
                                    <Grid
                                        item
                                        xs={12}
                                    >
                                        <Accordion>
                                            <AccordionSummary
                                                expandIcon={<ExpandMoreIcon />}
                                            >
                                                <Typography
                                                    align={"center"}
                                                    variant={"h4"}
                                                >
                                                    Instructions:
                                                </Typography>
                                            </AccordionSummary>
                                            <AccordionDetails>
                                                <Grid
                                                    container
                                                    justify={"space-evenly"}
                                                    direction={"column"}
                                                    className={"flex-grid"}
                                                >
                                                    {
                                                        this.state.data.instructions.map(
                                                            (item: InstructionModel, index: number) => (
                                                                <Typography
                                                                    variant={"h6"}
                                                                    key={index * 300}
                                                                >
                                                                    <ArrowRightIcon
                                                                        fontSize={"large"}
                                                                    />
                                                                    {item.instruction}
                                                                </Typography>
                                                            )
                                                        )
                                                    }
                                                </Grid>
                                            </AccordionDetails>
                                        </Accordion>
                                    </Grid>
                                </Grid>
                            </Card>
                        </motion.div> :
                        <Backdrop  open={this.state.isLoading}>
                            <CircularProgress color="inherit" />
                        </Backdrop>
                }
            </div>
        );
    }
}

export default Recipe;
