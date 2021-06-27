import React from 'react';
import './Register.css';
import { motion } from "framer-motion"
import {Link} from 'react-router-dom';
import '../../shared/styles/autentication/styles-authentication.css';
import ResponseData from "../../types/http/ResponseData";
import RegisterModel from "../../types/models/RegisterModel";
import UserService from '../../services/UserService';
import {toast} from "react-hot-toast";
import MyError from "../../types/http/MyError";
import * as Yup from 'yup';
import { Formik, Form, Field } from 'formik';
// @ts-ignore
import LoadingMask from "react-loadingmask";
import "react-loadingmask/dist/react-loadingmask.css";

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
            .min(3, "Too Short!")
            .max(20, "Too Long!")
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
                response = response as ResponseData<MyError>
                this.setState({isLoading: false});
                throw new Error();
            }
            response = response as ResponseData<RegisterModel>;
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
            <LoadingMask
                className={"container-custom"}
                loading={this.state.isLoading}
                text="Loading"
            >
                <div className={"container-custom"}>
                    <motion.div
                        animate={{ y: 0, opacity: 1 }}
                        initial={{y: -100, opacity: 0}}
                        transition={{ ease: "easeOut", duration: 1 }}
                        className={"container-main container-register width-container shadow"}
                    >
                        <motion.p
                            className={"text-container-title"}
                            animate={{opacity: 1 }}
                            initial={{opacity: 0}}
                            transition={{ ease: "easeOut", duration: 1 }}
                        >
                            Register
                        </motion.p>
                        <Formik
                            initialValues={this.initialValues}
                            validationSchema={this.schema}
                            onSubmit={(values, actions) =>{
                                this.setState({username: values.username});
                                this.setState({password: values.password});
                                this.setState({email: values.email});
                                this.register().then(r => {});
                            }}
                        >{
                            (
                                {
                                    handleSubmit,
                                    handleChange,
                                    handleBlur,
                                    values,
                                    touched,
                                    isValid,
                                    errors,
                                }
                            )=>(
                                <Form
                                    className={"container"}
                                >
                                    <div
                                        className={"form-group y-margin"}
                                    >
                                        <label
                                            htmlFor="username"
                                        >
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
                                    <div
                                        className={"form-group y-margin"}
                                    >
                                        <label
                                            htmlFor="password"
                                        >
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
                                    <div
                                        className={"form-group y-margin"}
                                    >
                                        <label
                                            htmlFor="validatePassword"
                                        >
                                            Validate password
                                        </label>
                                        <Field
                                            id="validatePassword"
                                            name="validatePassword"
                                            placeholder="Retype password"
                                            type={"password"}
                                            className={"input-text"}
                                        />
                                        {
                                            errors.validatePassword && touched.validatePassword?
                                                (<p className={"error-text"}>{errors.validatePassword}</p>)
                                                : null
                                        }
                                    </div>
                                    <div
                                        className={"form-group y-margin"}
                                    >
                                        <label
                                            htmlFor="validatePassword"
                                        >
                                            Email
                                        </label>
                                        <Field
                                            id="email"
                                            name="email"
                                            placeholder="Email..."
                                            type={"email"}
                                            className={"input-text"}
                                        />
                                        {
                                            errors.email && touched.email ?
                                                (<p className={"error-text"}>{errors.email}</p>)
                                                : null
                                        }
                                    </div>
                                    <button
                                        className={"button-form y-margin"}
                                        type="submit"
                                    >
                                        Register
                                    </button>
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
            </LoadingMask>
        );
    }
}

export default Register;
