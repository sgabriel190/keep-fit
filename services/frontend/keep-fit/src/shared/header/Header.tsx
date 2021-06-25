import "./Header.css";
import React from "react";
import { Nav, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import navbarRoutes from "../../routes/navbar-routes";
import { Link } from "react-router-dom";
import {BoxArrowInRight, BoxArrowRight} from "react-bootstrap-icons";
import selectJwtValue from "../../helpers/selector";
import store from "../../helpers/store";
import UserService from "../../services/UserService";

class Header extends React.Component<any, any>{

    constructor(props: any) {
        super(props);
        this.state = {
            loggedIn: sessionStorage.getItem("jwt") !== null,
        };
    }

    renderIcon(){
        if (selectJwtValue() === ""){
            this.setState({loggedIn: false});
        } else {
            this.setState({loggedIn: true});
        }
    }

    render() {
        store.subscribe(() => this.renderIcon());
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
                            navbarRoutes.map( (token, idx) => {
                                return(
                                    <Link
                                        className={"text-link-decoration px-3 py-3 mx-2"}
                                        to={token.url}
                                        key={idx}
                                    >
                                        {token.name}
                                    </Link>
                                );
                            })
                        }
                    </Nav>
                </Navbar.Collapse>
                {
                    this.state.loggedIn ?
                        <Link
                            to={"/login"}
                            className={"login-icon"}
                            onClick={() => UserService.logout()}
                        >
                            <BoxArrowRight/>
                        </Link> : <Link
                            to={"/login"}
                            className={"login-icon"}
                        >
                            <BoxArrowInRight/>
                        </Link>
                }
            </Navbar>
        );
    }
}

export default Header;
