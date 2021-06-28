import "./Header.css";
import React from "react";
import { Nav, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import navbarRoutes from "../../routes/navbar-routes";
import { Link } from "react-router-dom";
import {BoxArrowInRight, BoxArrowRight} from "react-bootstrap-icons";
import selectJwtValue from "../../helpers/selector";
import UserService from "../../services/UserService";
import {toast} from "react-hot-toast";
import store from "../../helpers/store";
import WebInfo from "../../services/WebInfo";

class Header extends React.Component<any, any>{

    unsubscribe;
    interceptorId: number;

    constructor(props: any) {
        super(props);
        this.state = {
            loggedIn: sessionStorage.getItem("jwt") !== null
        };
        this.interceptorId = WebInfo.httpClient.interceptors.response.use(
            undefined,
            (error: any) => {
                if(error.response.data.code === 401){
                    UserService.logout();
                    this.props.history.push("/login");
                }
                return Promise.reject(error);
            }
        );
        this.unsubscribe = store.subscribe(() => this.renderIcon());
    }

    renderIcon(){
        if (selectJwtValue() === null){
            this.setState({loggedIn: false});
        } else {
            this.setState({loggedIn: true});
        }
    }

    componentWillUnmount() {
        // Clear the subscription
        this.unsubscribe();
        WebInfo.httpClient.interceptors.response.eject(this.interceptorId);
    }

    render() {
        return(
            <Navbar bg={"dark"} variant={"dark"} className={"sticky-top header-custom py-0"}>
                <Navbar.Brand className={"px-0 py-0 mx-0"}>
                    <Link
                        className={"mx-1"}
                        to={"/"}
                    >
                        <img
                            alt="logo"
                            src="/logo-navbar.png"
                            className="d-inline-block align-top text-link-decoration py-2 "
                        />
                    </Link>
                </Navbar.Brand>
                <Navbar.Toggle aria-controls="basic-navbar-nav" />
                <Navbar.Collapse id="basic-navbar-nav">
                    {
                        this.state.loggedIn ?
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
                            : null
                    }
                </Navbar.Collapse>
                {
                    this.state.loggedIn ?
                        <Link
                            to={"/login"}
                            className={"login-icon"}
                            onClick={() => {UserService.logout(); toast.success("Successfully logged out.");}}
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
