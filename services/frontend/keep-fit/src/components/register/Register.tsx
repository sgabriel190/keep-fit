import React from 'react';
import './Register.css';
import { motion } from "framer-motion"
import {Link} from 'react-router-dom';
import '../../shared/styles/autentication/styles-authentication.css';
import ResponseData from "../../types/http/ResponseData";
import UserService from '../../services/UserService';
import {toast} from "react-hot-toast";
import * as Yup from 'yup';
import { Formik, Form } from 'formik';
import "react-loadingmask/dist/react-loadingmask.css";
import {Backdrop, Button, CircularProgress, Grid, Paper, TextField} from "@material-ui/core";
import AccountCircleIcon from "@material-ui/icons/AccountCircle";
import LockIcon from "@material-ui/icons/Lock";
import EmailIcon from '@material-ui/icons/Email';

interface FormValues {
    username: string;
    password: string;
    validatePassword: string;
    email: string;
}

class Register extends React.Component<any, any>{

    initialValues: FormValues = {username:'', password:'', validatePassword:'', email:''};

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
            .required('Required field'),
        validatePassword: Yup
            .string()
            .test("password-match", "Passwords must match!", function (value) {
                return this.parent.password === value;
            })
            .required('Required field'),
        email: Yup
            .string()
            .email('Invalid email!')
            .required('Required field')
    });

    constructor(props: object) {
        super(props);
        this.state = {
            username: null,
            password: null,
            email: null,
            isLoading: false
        };
    }

    async register() {
        try {
            this.setState({isLoading: true});
            let response: ResponseData<any> = await UserService.register({
                username: this.state.username,
                password: this.state.password,
                email: this.state.email
            });
            if (!response.successfulOperation){
                this.setState({isLoading: false});
                throw new Error();
            }
            this.setState({isLoading: false});
            toast.success('Register successfully');
            this.props.history.push("/login");
        }
        catch (e) {
            toast.error(`Register failed!`)
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
                    className={"container-main container-register width-container shadow"}
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
                            Register
                        </motion.p>
                    </Grid>
                    <Formik
                        initialValues={this.initialValues}
                        validationSchema={this.schema}
                        validateOnChange={false}
                        validateOnBlur={false}
                        onSubmit={(values, actions) =>{
                            this.setState({username: values.username});
                            this.setState({password: values.password});
                            this.setState({email: values.email});
                            this.register().then(r => {});
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
                            <Form
                                className={"container"}
                                autoComplete={"off"}
                            >
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
                                                id="validatePassword"
                                                name="validatePassword"
                                                label="Validate password"
                                                value={values.validatePassword}
                                                error={Boolean(errors.validatePassword) && touched.validatePassword}
                                                onChange={handleChange("validatePassword")}
                                                helperText={errors.validatePassword}
                                                type={"password"}
                                            />
                                        </Grid>
                                    </Grid>
                                    <Grid className={"y-margin"} container spacing={1} alignItems="center" justify={"center"}>
                                        <Grid item>
                                            <EmailIcon
                                                fontSize={"large"}
                                            />
                                        </Grid>
                                        <Grid item xs={7}>
                                            <TextField
                                                fullWidth={true}
                                                variant={"filled"}
                                                id="email"
                                                name="email"
                                                label="Email"
                                                value={values.email}
                                                error={Boolean(errors.email) && touched.email}
                                                onChange={handleChange("email")}
                                                helperText={errors.email}
                                                type={"email"}
                                            />
                                        </Grid>
                                    </Grid>
                                </Paper>
                                <Button
                                    size={"large"}
                                    variant={"contained"}
                                    className={"y-margin"}
                                    color={"primary"}
                                    type="submit"
                                >
                                    Register
                                </Button>
                                <Link
                                    className={"link-text-inherit y-margin"}
                                    to={"/login"}
                                >
                                    <span className={"span-clickable-form"} >
                                        Go back to log in.
                                    </span>
                                </Link>
                            </Form>
                        )
                    }
                    </Formik>
                </motion.div>
            </div>
        );
    }
}

export default Register;
