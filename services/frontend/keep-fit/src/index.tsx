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
import { Toaster } from 'react-hot-toast';
import {GuardedRoute, GuardProvider} from "react-router-guards";
import Welcome from "./components/welcome/Welcome";
import Login from "./components/login/Login";
import Register from "./components/register/Register";
import Recipe from "./components/recipes/Recipe/Recipe";

const requireLogin = (to: any, from: any, next: any) => {
    if(sessionStorage.getItem("jwt") === null){
        next.redirect('/login');
    } else {
        next();
    }
}

axios.interceptors.request.use(
    (request) => {
        if (request.url?.includes("login") || request.url?.includes("register")){
            return request;
        }
        if(selectJwtValue() !== null){
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
            <Toaster
                toastOptions={{
                    style: {
                        marginTop: "3.5em"
                    },
                    ariaProps: {
                        role: 'status',
                        'aria-live': 'polite',
                    },
                    duration: 3000,
                    position: "top-center"
                }}
            />
            <GuardProvider guards={[requireLogin]}>
                <Switch>
                    {
                        routes.map( (token, idx) => {
                                return(
                                    <GuardedRoute
                                        key={idx}
                                        path={token.url}
                                        exact={true}
                                        component={token.component}
                                        meta={{auth: true}}
                                    />
                                );
                            }
                        )
                    }
                    <GuardedRoute
                        path={"/recipe/:recipeId"}
                        exact={true}
                        component={Recipe}
                        meta={{auth: true}}
                    />
                    <Route
                        path={"/"}
                        component={Welcome}
                        exact={true}
                    />
                    <Route
                        path={"/login"}
                        component={Login}
                        exact={true}
                    />
                    <Route
                        path={"/register"}
                        component={Register}
                        exact={true}
                    />
                    <Route
                        component={NotFound}
                    />
                </Switch>
            </GuardProvider>
        </Router>
    </React.StrictMode>
    ,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals(console.log);
