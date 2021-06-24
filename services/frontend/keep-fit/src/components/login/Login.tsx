import React from 'react';
import './Login.css';
import '../../shared/styles/shared.css';
import '../../shared/styles/autentication/styles-authentication.css';
import {Container, Form, InputGroup, Row} from 'react-bootstrap';
import {Person, ShieldLock} from "react-bootstrap-icons";
import {Link} from 'react-router-dom';
import { motion } from "framer-motion"
import ResponseData from "../../types/models/ResponseData";
import AuthModel from "../../types/models/AuthModel";
import store from "../../helpers/store";
import addJwt from "../../helpers/actions";
import UserService from '../../services/UserService';
import selectJwtValue from "../../helpers/selector";

class Login extends React.Component<any, any>{
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
            let response: ResponseData<AuthModel> = await UserService.login({
                username: this.state.username,
                password: this.state.password
            });
            if (!response.successfulOperation){
                throw new Error(response.error);
            }
            store.dispatch(addJwt(response.data.token));
            console.log(selectJwtValue());
            this.props.history.push("/");
        }
        catch (e) {
            console.log(e);
        }
    }

    render() {
        return (
            <div className={"container-custom py-5"}>
                <motion.div
                    animate={{ y: 0, opacity: 1 }}
                    initial={{y: -100, opacity: 0}}
                    transition={{ ease: "easeOut", duration: 1 }}
                    className={"container-main container-login p-5 shadow"}
                >
                    <motion.p
                        className={"text-container-title"}
                        animate={{opacity: 1 }}
                        initial={{opacity: 0}}
                        transition={{ ease: "easeOut", duration: 1 }}
                    >Log in</motion.p>
                    <Form as={Container}
                          className={"pt-4"}
                    >
                        <Row>
                            <Form.Group>
                                <Row className={"col-lg-7 my-2"}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>
                                                <Person />
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control
                                            type="text"
                                            placeholder="Enter username..."
                                            onChange={e=>this.setState({username: e.target.value})}
                                        />
                                    </InputGroup>
                                </Row>
                            </Form.Group>
                            <Form.Group controlId="formBasicPassword">
                                <Row className={"col-lg-7 my-2"}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>
                                                <ShieldLock />
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control
                                            type="password"
                                            placeholder="Password"
                                            onChange={e=>this.setState({password: e.target.value})}
                                        />
                                    </InputGroup>
                                </Row>
                            </Form.Group>
                            <Form.Group controlId="formBasicCheckbox">
                                <Row>
                                    <Form.Check type="checkbox" label="Remember my credentials" />
                                </Row>
                            </Form.Group>
                        </Row>
                        <Row className={"pb-3"}>
                            <button
                                className={"button-form"}
                                onClick={this.login.bind(this)}
                            >
                                Log in
                            </button>
                        </Row>
                        <Row className={"pt-3 pb-2"}>
                            <Link to={"/register"}>
                                <button className={"button-form"}>
                                    Register
                                </button>
                            </Link>
                        </Row>
                        <Row className={"pt-2"}>
                                <span
                                    className={"span-clickable-form"}
                                    onClick={() => {console.log("clicked forgot password")}}
                                >
                                    I have forgot my password.
                                </span>
                        </Row>
                    </Form>
                </motion.div>
            </div>
        );
    }
}

export default Login;
