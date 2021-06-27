import React, {Component} from "react";
import ResponseData from "../../../types/http/ResponseData";
import NutritionService from "../../../services/NutritionService";
import MyError from "../../../types/http/MyError";
import RecipeLiteModel from "../../../types/models/RecipeLiteModel";
import {toast} from "react-hot-toast";
import {Backdrop, CircularProgress} from "@material-ui/core";
import {motion} from "framer-motion";
import './Recipe.css';

class Recipe extends Component<any, any>{

    constructor(props: object) {
        super(props);
        this.state = {
            data: null,
            id: this.props.match.params.recipeId,
            isLoading: true
        };
    }

    async componentDidMount(){
        try {
            let response: ResponseData<any> = await NutritionService.getRecipe(this.state.id);
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                throw new Error(response.error);
            }
            response = response as ResponseData<RecipeLiteModel>;
            console.log(response.data);
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
                            <p>test </p>
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
