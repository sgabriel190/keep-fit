import React from 'react';
import './Recipes.css';
import ResponseData from "../../types/http/ResponseData";
import MyError from "../../types/http/MyError";
import {toast} from "react-hot-toast";
import NutritionService from "../../services/NutritionService";
import RecipeLiteModel from "../../types/models/RecipeLiteModel";
import {motion} from "framer-motion";
import {
    Backdrop,
    Card,
    CardActionArea,
    CardContent,
    CardMedia,
    CircularProgress,
    Grid,
    Typography
} from "@material-ui/core";

class Recipes extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            data: null,
            isLoading: true
        };
    }

    async componentDidMount(){
        try {
            let response: ResponseData<any> = await NutritionService.getRecipes({pagSize: 12});
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                throw new Error(response.error);
            }
            response = response as ResponseData<RecipeLiteModel[]>;
            console.log(response.data);
            this.setState({data: response.data});
            this.setState({isLoading: false});
        }
        catch (e) {
            toast.error(`Loading recipes failed!\n${e}`)
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
                            className={"container-main shadow container-recipes container-size"}>
                            <Grid
                                container
                                spacing={5}
                            >
                                {
                                    this.state.data.map(
                                        (value:RecipeLiteModel) => (
                                            <Grid
                                                xs={4}
                                                item
                                            >
                                                <Card
                                                    style={{
                                                        display: "block",
                                                        height: "30vw"
                                                    }}
                                                    onClick={() => console.log("click")}
                                                >
                                                    <CardActionArea>
                                                        <CardMedia
                                                            src={"https://jonathansexton.me/blog/wp-content/uploads/2019/10/image.png"}
                                                            title={"Title"}
                                                            component={"img"}
                                                            alt={"Recipe image"}
                                                        />
                                                        <Typography
                                                            variant="h4"
                                                            align={"center"}
                                                            gutterBottom={true}
                                                            color={"primary"}
                                                            key={value.id * 10}
                                                        >
                                                            {value.name}
                                                        </Typography>
                                                    </CardActionArea>
                                                    <CardContent>
                                                        <Typography
                                                            variant={"subtitle2"}
                                                            align={"center"}
                                                            key={value.id * 20}
                                                        >
                                                            {value.description}
                                                        </Typography>
                                                        <Typography
                                                            align={"center"}
                                                            variant="overline"
                                                            key={value.id * 30}
                                                        >
                                                            Keywords: {value.keywords}
                                                        </Typography>
                                                        <Typography
                                                            align={"center"}
                                                            variant="body2"
                                                            key={value.id * 40}
                                                        >
                                                            <p><b>Preparation time</b>: {value.timeTotal.prepTime}</p>
                                                            <p><b>Cook time</b>: {value.timeTotal.cookTime}</p>
                                                            <p><b>Total time</b>: {value.timeTotal.totalTime}</p>
                                                        </Typography>
                                                    </CardContent>
                                                </Card>
                                            </Grid>
                                        )
                                    )
                                }
                            </Grid>
                        </motion.div>
                        : <Backdrop  open={this.state.isLoading}>
                            <CircularProgress color="inherit" />
                        </Backdrop>
                }
            </div>
        );
    }
}

export default Recipes;
