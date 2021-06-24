import React from 'react';
import './Register.css';
import {Container, Form, InputGroup, Row} from "react-bootstrap";
import {Envelope, Person, ShieldLock} from "react-bootstrap-icons";
import { motion } from "framer-motion"
import {Link} from 'react-router-dom';
import '../../shared/styles/autentication/styles-authentication.css';
import ResponseData from "../../types/models/ResponseData";
import RegisterModel from "../../types/models/RegisterModel";
import UserService from '../../services/UserService';

class Register extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            username: null,
            password: null,
            checkPassword: null,
            email: null
        };
    }

    async componentDidMount(){
    }

    async register() {
        try {
            let data: ResponseData<RegisterModel> = await UserService.register({
                username: this.state.username,
                password: this.state.password,
                email: this.state.email
            });
            if (!data.successfulOperation){
                throw new Error(data.error);
            }
            console.log(data);
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
                    className={"container-main container-register p-5 shadow"}
                >
                    <motion.p
                        className={"text-container-title"}
                        animate={{opacity: 1 }}
                        initial={{opacity: 0}}
                        transition={{ ease: "easeOut", duration: 1 }}
                    >
                        Register
                    </motion.p>
                    <Form
                        as={Container}
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
                            <Form.Group>
                                <Row className={"col-lg-7 my-2"}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>
                                                <ShieldLock />
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control
                                            type="password"
                                            placeholder="Validate password"
                                            onChange={e=>this.setState({checkPassword: e.target.value})}
                                        />
                                    </InputGroup>
                                </Row>
                            </Form.Group>
                            <Form.Group>
                                <Row className={"col-lg-7 my-2"}>
                                    <InputGroup>
                                        <InputGroup.Prepend>
                                            <InputGroup.Text>
                                                <Envelope />
                                            </InputGroup.Text>
                                        </InputGroup.Prepend>
                                        <Form.Control
                                            type="email"
                                            placeholder="Email"
                                            onChange={e=>this.setState({email: e.target.value})}
                                        />
                                    </InputGroup>
                                </Row>
                            </Form.Group>
                        </Row>
                        <Row className={"pt-3 pb-2"}>
                            <button
                                className={"button-form"}
                                onClick={this.register.bind(this)}
                            >
                                Register
                            </button>
                        </Row>
                        <Row className={"pt-2"}>
                            <Link
                                className={"link-text-inherit"}
                                to={"/login"}
                            >
                                <span className={"span-clickable-form"} >
                                    Go back to log in.
                                </span>
                            </Link>
                        </Row>
                    </Form>
                </motion.div>
            </div>
        );
    }
}

export default Register;
