import React from 'react';
import './Login.css';
import '../../shared/styles/shared.css';
import {Container, Form, InputGroup, Row} from 'react-bootstrap';
import {Person, ShieldLock} from "react-bootstrap-icons";
import {Link} from 'react-router-dom';

class Login extends React.Component<any, any>{
    render() {
        return (
            <div className={"container-custom py-5"}>
                <div className={"container-main container-login p-5 shadow"}>
                    <p className={"text-container-title"}>Log in</p>
                    <Form as={Container} className={"pt-4"}>
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
                            <Form.Group controlId="formBasicCheckbox">
                                    <Row>
                                        <Form.Check type="checkbox" label="Remember my credentials" />
                                    </Row>
                            </Form.Group>
                            </Row>
                            <Row className={"pb-3"}>
                                <button className={"button-form"} onClick={() => {console.log("clicked login")}}>
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
                                <span className={"span-forgot-password"} onClick={() => {console.log("clicked forgot password")}}>
                                    I have forgot my password.
                                </span>
                            </Row>
                    </Form>
                </div>
            </div>
        );
    }
}

export default Login;
