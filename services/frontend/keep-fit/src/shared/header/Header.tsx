import "./Header.css";
import React from "react";
import {Nav, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import routes from "../../routes/routes";
import { Link } from "react-router-dom";

class Header extends React.Component<any, any>{
    render() {
        return(
            <Navbar bg={"dark"} variant={"dark"} className={"sticky-top header-custom"}>
                <Navbar.Brand>
                    <Link
                        className={"text-link-decoration px-3 py-2 mx-1"}
                        to={"/"}
                    >
                        Keep Fit
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
                                            className={"text-link-decoration px-3 py-2 mx-1"}
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
                </Navbar.Collapse>
            </Navbar>
        );
    }
}

export default Header;
