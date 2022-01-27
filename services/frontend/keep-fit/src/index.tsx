import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {Router, Route, Switch} from "react-router-dom";
import Header from "./shared/header/Header";
import routes from "./routes/routes";
import NotFound from "./components/notfound/NotFound";
import { Toaster } from 'react-hot-toast';
import {GuardedRoute, GuardProvider} from "react-router-guards";
import Welcome from "./components/welcome/Welcome";
import Login from "./components/login/Login";
import Register from "./components/register/Register";
import requestAccept from "./interceptors/RequestAccept";
import WebInfo from "./services/WebInfo";
import history from "./helpers/History";


const requireLogin = (to: any, from: any, next: any) => {
    if(sessionStorage.getItem("jwt") === null){
        next.redirect('/login');
    } else {
        next();
    }
}


WebInfo.httpClient.interceptors.request.use(
    requestAccept
);

WebInfo.httpClient.interceptors.response.use();

ReactDOM.render(
    <React.StrictMode>
        <Router
            history={history}
        >
            <Header
                history={history}
            />
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
