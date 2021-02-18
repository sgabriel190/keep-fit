import "./Header.css";
import React from "react";
import {Button, Form, FormControl, Nav, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import routes from "../../routes/routes";
import { Link } from "react-router-dom";
import {BoxArrowInRight} from "react-bootstrap-icons";

class Header extends React.Component<any, any>{
    render() {
        return(
            <Navbar bg={"dark"} variant={"dark"} className={"sticky-top header-custom py-0"}>
                <Navbar.Brand className={"px-0 py-0 mx-0"}>
                    <Link
                        className={"mx-1"}
                        to={"/"}
                    >
                        <img
                            alt=""
                            src="/logo-navbar.png"
                            className="d-inline-block align-top text-link-decoration py-2 "
                        />
                    </Link>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    <Nav>
                        {
                            routes.map( (token, idx) => {
                                return(
                                    token.url !== "/" ?
                                        <Link
                                            className={"text-link-decoration px-3 py-3 mx-2"}
                                            to={token.url}
                                            key={idx}
                                        >
                                            {token.name}
                                        </Link> :
                                        null
                                );
                            })
                        }
                    </Nav>
                    <Form inline>
                        <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                        <Button variant="outline-primary">Search</Button>
                    </Form>
                </Navbar.Collapse>
                <Link
                    to={"/login"}
                    className={"login-icon"}
                >
                    <BoxArrowInRight/>
                </Link>
            </Navbar>
        );
    }
}

export default Header;
