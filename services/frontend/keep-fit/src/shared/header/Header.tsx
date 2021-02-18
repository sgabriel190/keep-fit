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
                        className={"px-3 py-2 mx-1"}
                        to={"/"}
                    >
                        <img
                            alt="logo"
                            src="/logo-navbar.png"
                            width="150"
                            height="70"
                            className="d-inline-block align-top text-link-decoration"
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
