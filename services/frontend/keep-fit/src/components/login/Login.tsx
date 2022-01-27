import React from 'react';
import './Login.css';
import '../../shared/styles/shared.css';
import '../../shared/styles/autentication/styles-authentication.css';
import {Link} from 'react-router-dom';
import { motion } from "framer-motion"
import ResponseData from "../../types/http/ResponseData";
import AuthModel from "../../types/models/user/AuthModel";
import UserService from '../../services/UserService';
import {toast} from "react-hot-toast";
import MyError from "../../types/http/MyError";
import * as Yup from 'yup';
import { Formik, Form } from 'formik';
import store from "../../helpers/store";
import addJwt from "../../helpers/action";
import {Backdrop, Button, CircularProgress, Grid, Paper, TextField} from "@material-ui/core";
import AccountCircleIcon from '@material-ui/icons/AccountCircle';
import LockIcon from '@material-ui/icons/Lock';

interface FormValues {
    username: string;
    password: string;
}

class Login extends React.Component<any, any>{

    initialValues: FormValues = {username:'', password:''};

    schema = Yup.object().shape({
        username: Yup
            .string()
            .min(3, "Too Short!")
            .max(20, "Too Long!")
            .required('Required field'),
        password: Yup
            .string()
            .min(3, "Too Short!")
            .max(20, "Too Long!")
            .required('Required field')
    });

    constructor(props: object) {
        super(props);
        this.state = {
            username: null,
            password: null,
            isLoading: false
        };
        UserService.logout();
    }

    async componentDidMount(){
    }

    async login() {
        try {
            this.setState({isLoading: true});
            let response: ResponseData<any> = await UserService.login({
                username: this.state.username,
                password: this.state.password
            });
            this.setState({isLoading: false});
            if (!response.successfulOperation){
                response = response as ResponseData<MyError>;
                throw new Error(response.error);
            }
            response = response as ResponseData<AuthModel>;
            sessionStorage.setItem("jwt", response.data.token);
            store.dispatch(addJwt);
            toast.success('Login successfully');
            this.props.history.push("/");
        }
        catch (e) {
            toast.error(`Login failed!`)
        }
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
                    className={"container-main container-login shadow width-container"}
                >
                    <Grid
                        container
                        justify={"center"}
                    >
                        <motion.p
                            className={"text-title custom-title-text"}
                            animate={{opacity: 1 }}
                            initial={{opacity: 0}}
                            transition={{ ease: "easeOut", duration: 1 }}
                        >
                            Log in
                        </motion.p>
                    </Grid>
                    <Formik
                        initialValues={this.initialValues}
                        validateOnChange={false}
                        validateOnBlur={false}
                        validationSchema={
                            this.schema
                        }
                        onSubmit={(values) =>{
                            this.setState({username: values.username});
                            this.setState({password: values.password});
                            this.login().then(r => {});
                        }}
                    >{
                        (
                            {
                                values,
                                touched,
                                errors,
                                handleChange
                            }
                        )=>(
                            <Form className={"container"} autoComplete={"off"}>
                                <Paper className={"container input-paper"}>
                                    <Grid className={"y-margin"} container spacing={1} alignItems="center" justify={"center"}>
                                        <Grid item>
                                            <AccountCircleIcon
                                                fontSize={"large"}
                                            />
                                        </Grid>
                                        <Grid item xs={7}>
                                            <TextField
                                                fullWidth={true}
                                                variant={"filled"}
                                                id="username"
                                                name="username"
                                                label={"Username"}
                                                value={values.username}
                                                error={!!errors.username && touched.username}
                                                onChange={handleChange("username")}
                                                helperText={errors.username}
                                                type={"text"}
                                            />
                                        </Grid>
                                    </Grid>
                                    <Grid className={"y-margin"} container spacing={1} alignItems="center" justify={"center"}>
                                        <Grid item>
                                            <LockIcon
                                                fontSize={"large"}
                                            />
                                        </Grid>
                                        <Grid item xs={7}>
                                            <TextField
                                                fullWidth={true}
                                                variant={"filled"}
                                                id="password"
                                                name="password"
                                                label="Password"
                                                value={values.password}
                                                error={Boolean(errors.password) && touched.password}
                                                onChange={handleChange("password")}
                                                helperText={errors.password}
                                                type={"password"}
                                            />
                                        </Grid>
                                    </Grid>
                                </Paper>
                                <Grid container justify={"center"} spacing={7}>
                                    <Grid item>
                                        <Button
                                            size={"large"}
                                            variant={"contained"}
                                            className={"y-margin"}
                                            color={"primary"}
                                            type="submit"
                                        >
                                            Log in
                                        </Button>
                                    </Grid>
                                    <Grid item>
                                        <Link to={"/register"}>
                                            <Button
                                                size={"large"}
                                                color={"primary"}
                                                variant={"contained"}
                                                className={"y-margin"}>
                                                Register
                                            </Button>
                                        </Link>
                                    </Grid>
                                </Grid>
                            </Form>
                        )
                    }
                    </Formik>
                </motion.div>
            </div>
        );
    }
}

export default Login;
