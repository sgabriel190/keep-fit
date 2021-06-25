import React from 'react';
import './Login.css';
import '../../shared/styles/shared.css';
import '../../shared/styles/autentication/styles-authentication.css';
import {Link} from 'react-router-dom';
import { motion } from "framer-motion"
import ResponseData from "../../types/models/ResponseData";
import AuthModel from "../../types/models/AuthModel";
import UserService from '../../services/UserService';
import {toast} from "react-hot-toast";
import MyError from "../../types/models/MyError";
import * as Yup from 'yup';
import { Formik, Form, Field } from 'formik';
import store from "../../helpers/store";
import addJwt from "../../helpers/action";

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
            password: null
        };
    }

    async componentDidMount(){
    }

    async login() {
        try {
            let response: ResponseData<any> = await UserService.login({
                username: this.state.username,
                password: this.state.password
            });
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
            toast.error(`Login failed!\n${e}`)
        }
    }

    render() {
        return (
            <div className={"container-custom"}>
                <motion.div
                    animate={{ y: 0, opacity: 1 }}
                    initial={{y: -100, opacity: 0}}
                    transition={{ ease: "easeOut", duration: 1 }}
                    className={"container-main container-login shadow width-container"}
                >
                    <motion.p
                        className={"text-container-title"}
                        animate={{opacity: 1 }}
                        initial={{opacity: 0}}
                        transition={{ ease: "easeOut", duration: 1 }}
                    >Log in</motion.p>
                    <Formik
                        initialValues={this.initialValues}
                        validationSchema={this.schema}
                        onSubmit={(values) =>{
                            this.setState({username: values.username});
                            this.setState({password: values.password});
                            this.login().then(r => {});
                        }}
                    >{
                        (
                            {
                                touched,
                                errors,
                            }
                        )=>(
                            <Form className={"container"}>
                                <div className={"form-group y-margin"}>
                                    <label htmlFor="username">
                                        Username
                                    </label>
                                    <Field
                                        id="username"
                                        name="username"
                                        placeholder="Username..."
                                        type={"text"}
                                        className={"input-text"}
                                    />
                                    {
                                        errors.username && touched.username ?
                                            (<p className={"error-text"}>{errors.username}</p>)
                                            : null
                                    }
                                </div>
                                <div className={"form-group y-margin"}>
                                    <label htmlFor="password">
                                        Password
                                    </label>
                                    <Field
                                        id="password"
                                        name="password"
                                        placeholder="Password..."
                                        type={"password"}
                                        className={"input-text"}
                                    />
                                    {
                                        errors.password && touched.password ?
                                            (<p className={"error-text"}>{errors.password}</p>)
                                            : null
                                    }
                                </div>
                                <button
                                    className={"button-form y-margin"}
                                    type="submit"
                                >
                                    Log in
                                </button>
                                <Link to={"/register"}>
                                    <button className={"button-form y-margin"}>
                                        Register
                                    </button>
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

export default Login;
