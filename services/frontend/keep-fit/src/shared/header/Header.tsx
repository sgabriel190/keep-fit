import "./Header.css";
import React from "react";
import {Nav, Navbar} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import routes from "../../routes/routes";

class Header extends React.Component<any, any>{
    render() {
        return(
            <Navbar bg={"dark"} variant={"dark"} className={"sticky-top"}>
                    <Navbar.Brand href="/">
                        Keep Fit
                    </Navbar.Brand>
                    <Navbar.Toggle aria-controls="basic-navbar-nav" />
                    <Navbar.Collapse id="basic-navbar-nav">
                        <Nav>
                            {
                                routes.map( (token, idx) => {
                                    return(
                                        <Nav.Item
                                            key={idx}
                                        >
                                            <Nav.Link
                                                key={idx*10}
                                                href={token.url}
                                                className={"mx-3 px-2"}
                                            >
                                                {token.name}
                                            </Nav.Link>
                                        </Nav.Item>
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
