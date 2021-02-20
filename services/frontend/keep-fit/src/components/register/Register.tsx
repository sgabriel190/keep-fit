import React from 'react';
import './Register.css';
import {Container, Form, InputGroup, Row} from "react-bootstrap";
import {Envelope, Person, ShieldLock} from "react-bootstrap-icons";
import { motion } from "framer-motion"
import {Link} from 'react-router-dom';
import '../../shared/styles/autentication/styles-authentication.css';

class Register extends React.Component<any, any>{
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
                                        <Form.Control type="text" placeholder="Enter username..." />
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
                                        <Form.Control type="password" placeholder="Password" />
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
                                        <Form.Control type="password" placeholder="Validate password" />
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
                                        <Form.Control type="email" placeholder="Email" />
                                    </InputGroup>
                                </Row>
                            </Form.Group>
                        </Row>
                        <Row className={"pt-3 pb-2"}>
                            <button
                                className={"button-form"}
                                onClick={() => {console.log("clicked register")}}
                            >
                                Register
                            </button>
                        </Row>
                        <Row className={"pt-2"}>
                            <Link
                                className={"link-text-inherit"}
                                to={"/login"}
                            >
                                <span className={"span-clickable-form"} onClick={() => {console.log("clicked back to login")}}>
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
