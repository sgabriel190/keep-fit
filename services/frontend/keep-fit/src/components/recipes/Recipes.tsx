import React from 'react';
import './Recipes.css';
import ResponseData from "../../types/http/ResponseData";
import MyError from "../../types/http/MyError";
import {toast} from "react-hot-toast";
import NutritionService from "../../services/NutritionService";
import RecipeLiteModel from "../../types/models/RecipeLiteModel";
import {motion} from "framer-motion";
import {
    Backdrop, Button, ButtonGroup,
    Card,
    CardActionArea,
    CardContent,
    CardMedia,
    CircularProgress, Divider, FormControl,
    Grid, IconButton, InputBase, InputLabel, MenuItem, Paper, Select,
    Typography
} from "@material-ui/core";
import CategoryModel from "../../types/models/CategoryModel";
import SearchIcon from '@material-ui/icons/Search';



class Recipes extends React.Component<any, any>{

    constructor(props: object) {
        super(props);
        this.state = {
            data: null,
            dataCategory: null,
            isLoading: true,
            page: 1,
            pageSize: 12,
            categorySelected: "",
            tmpData: null,
            searchValue: ""
        };

    }

    async componentDidMount(){
        try {
            let response: ResponseData<any> = await NutritionService.getRecipes({pagSize: this.state.pageSize, pagNumber: this.state.page});
            let responseCategories: ResponseData<any> = await NutritionService.getCategories();
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                let message = response.error.split(" ").slice(1).join(" ");
                throw new Error(message);
            }
            if (!responseCategories.successfulOperation){
                responseCategories = responseCategories as ResponseData<MyError>;
                let message = responseCategories.error.split(" ").slice(1).join(" ");
                throw new Error(message);
            }
            responseCategories = responseCategories as ResponseData<CategoryModel[]>;
            response = response as ResponseData<RecipeLiteModel[]>;
            this.setState({data: response.data});
            this.setState({dataCategory: responseCategories.data});
            this.setState({tmpData: Object.assign({}, response.data)});
            this.setState({isLoading: false});
        }
        catch (e) {
            toast.error(`Loading recipes failed!\n${e}`)
        }
    }

    setData(size: number){
        this.setState({pageSize: size}, () => {
            let temp = this.state.tmpData.slice(0, this.state.pageSize);
            this.setState({data: temp});
        });
    }

    getRecipes(page: number){
        if (page < 1){
            return;
        }
        NutritionService.getRecipes({pagSize: this.state.pageSize, pagNumber: page}).then((data: ResponseData<RecipeLiteModel[]>)=>{
            this.setState({data: data.data});
            this.setState({tmpData: data.data});
        });
        this.setState({page: page});
    }

    getRecipesByName(){
        if(this.state.searchValue !== ""){
            NutritionService.getRecipes({pagSize: this.state.pageSize, pagNumber: 1, recipeName: this.state.searchValue}).then((data: ResponseData<RecipeLiteModel[]>)=>{
                this.setState({data: data.data});
                this.setState({tmpData: data.data});
                this.setState({page: 1});
            });
        }
    }

    render() {
        const handleChange = (event: React.ChangeEvent<{ value: unknown }>) => {
            this.setState({categorySelected: event.target.value as string}, () => {
                if(this.state.categorySelected !== ""){
                NutritionService.getRecipes({pagSize: this.state.pageSize, pagNumber: 1, categoryName: this.state.categorySelected})
                    .then((data: ResponseData<RecipeLiteModel[]>)=>{
                    this.setState({data: data.data});
                    this.setState({page: 1});
                    this.setState({tmpData: data.data});
                });
            }
            });
        };
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
                                spacing={6}
                            >
                                <Grid
                                    container
                                    justify={"center"}
                                >
                                    <p className={"text-title custom-title-text"}>Recipes</p>
                                </Grid>
                                <Grid container justify={"space-evenly"} alignItems={"center"} spacing={3}>
                                    <Grid
                                        item
                                        xs={12}
                                    >
                                        <Grid
                                            container
                                            justify={"center"}
                                        >
                                            <Paper
                                                className={"paper-search"}
                                            >
                                                <InputBase
                                                    placeholder={"Search recipes"}
                                                    onChange={(data) => {this.setState({searchValue: data.target.value})}}
                                                />
                                                <Divider orientation={"vertical"}/>
                                                <IconButton
                                                    color="primary"
                                                    onClick={this.getRecipesByName.bind(this)}
                                                >
                                                    <SearchIcon/>
                                                </IconButton>
                                            </Paper>
                                        </Grid>
                                    </Grid>
                                    <Grid
                                        item
                                        xs={12}
                                    >
                                        <Grid
                                            container
                                            justify={"center"}
                                        >
                                            <Paper
                                                className={"select-width"}
                                            >
                                            <FormControl
                                                style={{
                                                    width: "100%"
                                                }}
                                                color={"primary"}
                                                variant={"filled"}
                                            >
                                                <InputLabel>Category</InputLabel>
                                                <Select
                                                    autoWidth={true}
                                                    value={this.state.categorySelected}
                                                    onChange={handleChange}
                                                >
                                                    <MenuItem value={""}>No category selected</MenuItem>
                                                    {
                                                        this.state.dataCategory.map(
                                                            (data: CategoryModel) => (
                                                                <MenuItem value={data.category}>{data.category}</MenuItem>
                                                            )
                                                        )
                                                    }
                                                </Select>
                                            </FormControl>
                                            </Paper>
                                        </Grid>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Typography
                                            align={"center"}
                                            color="textSecondary"
                                            variant={"h4"}
                                        >
                                            Recipes per page
                                        </Typography>
                                    </Grid>
                                    <Grid item xs={6}>
                                        <Typography
                                            align={"center"}
                                            color="textSecondary"
                                            variant={"h4"}
                                        >
                                            Page {this.state.page}
                                        </Typography>
                                    </Grid>
                                    <Grid item xs={6} className={"button-group-custom"} justify={"center"}>
                                        <ButtonGroup
                                            variant="contained"
                                            color="primary"
                                            aria-label="outlined primary button group"
                                            size={"large"}
                                        >
                                            <Button
                                                onClick={this.setData.bind(this, 6)}
                                            >
                                                6
                                            </Button>
                                            <Button
                                                onClick={this.setData.bind(this, 9)}
                                            >
                                                9
                                            </Button>
                                            <Button
                                                onClick={this.setData.bind(this, 12)}
                                            >
                                                12
                                            </Button>
                                        </ButtonGroup>
                                    </Grid>
                                    <Grid item xs={6} className={"button-group-custom"} justify={"center"}>
                                        <ButtonGroup
                                            variant="contained"
                                            color="primary"
                                            aria-label="outlined primary button group"
                                            size={"large"}
                                        >
                                            <Button
                                                onClick={this.getRecipes.bind(this, this.state.page - 1)}
                                            >
                                                Previous
                                            </Button>
                                            <Button
                                                onClick={this.getRecipes.bind(this, this.state.page + 1)}
                                            >
                                                Next
                                            </Button>
                                        </ButtonGroup>
                                    </Grid>
                                </Grid>
                                {
                                    this.state.data.map(
                                        (value:RecipeLiteModel) => (
                                            <Grid
                                                xs={4}
                                                item
                                                key={value.id * 40}
                                            >
                                                <Card
                                                    style={{
                                                        display: "block",
                                                        height: "35vw"
                                                    }}
                                                    onClick={() => console.log("click")}
                                                >
                                                    <CardActionArea
                                                        onClick={ () => this.props.history.push(`/recipe/${value.id}`)}
                                                    >
                                                        <CardMedia
                                                            src={`http://localhost:2025/api/backend/nutrition/image/${value.images.length !==1 ? value.images[3].imagePath : value.images[0].imagePath}`}
                                                            title={"Title"}
                                                            component={"img"}
                                                            alt={"Recipe image"}
                                                        />
                                                        <Typography
                                                            variant="h4"
                                                            align={"center"}
                                                            gutterBottom={true}
                                                            color={"primary"}
                                                        >
                                                            {value.name}
                                                        </Typography>
                                                    </CardActionArea>
                                                    <Divider />
                                                    <CardContent>
                                                        <Typography
                                                            variant={"subtitle2"}
                                                            align={"center"}
                                                            noWrap={true}
                                                            gutterBottom
                                                        >
                                                            {value.description}
                                                        </Typography>
                                                        <Typography
                                                            align={"center"}
                                                            variant="subtitle2"
                                                            noWrap={true}
                                                            gutterBottom
                                                        >
                                                            <b>Keywords</b>: {value.keywords}
                                                        </Typography>
                                                        <Divider />
                                                        <Typography
                                                            align={"center"}
                                                            variant="body2"
                                                            gutterBottom
                                                        >
                                                            <b>Preparation time</b>: {value.timeTotal.prepTime === "" ? "Unknown" : value.timeTotal.prepTime}
                                                        </Typography>
                                                        <Typography
                                                            align={"center"}
                                                            variant="body2"
                                                            gutterBottom
                                                        >
                                                            <b>Cook time</b>: {value.timeTotal.cookTime === "" ? "Unknown" : value.timeTotal.cookTime}
                                                        </Typography>
                                                        <Typography
                                                            align={"center"}
                                                            variant="body2"
                                                            gutterBottom
                                                        >
                                                            <b>Total time</b>: {value.timeTotal.totalTime === "" ? "Unknown" : value.timeTotal.totalTime}
                                                        </Typography>
                                                        <Divider />
                                                        <Typography
                                                            align={"center"}
                                                            variant="body2"
                                                            gutterBottom
                                                        >
                                                            <b>Categories</b>:
                                                            {
                                                                value.categories.map(
                                                                    (category: CategoryModel) => (
                                                                        category.category + ", "
                                                                    )
                                                                )
                                                            }
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

export default Recipes;
