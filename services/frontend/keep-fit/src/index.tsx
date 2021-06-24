import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Header from "./shared/header/Header";
import routes from "./routes/routes";
import NotFound from "./components/notfound/NotFound";
import axios from "axios";
import selectJwtValue from "./helpers/selector";

axios.interceptors.request.use(
    (request) => {
        if (request.url?.includes("login") || request.url?.includes("register")){
            return request;
        }
        if(selectJwtValue() !== ""){
            request.headers["Authorization"] = `Bearer ${selectJwtValue()}`;
        }
        return request;
    },
    undefined
);

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <Header/>
            <Switch>
                {
                    routes.map( (token, idx) => {
                            return(
                                <Route
                                    key={idx}
                                    path={token.url}
                                    exact={true}
                                    component={token.component}
                                />
                            );
                        }
                    )
                }
                <Route
                    component={NotFound}
                />
            </Switch>
        </Router>
    </React.StrictMode>
    ,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals(console.log);
