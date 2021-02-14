import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import Header from "./shared/header/Header";
import routes from "./routes/routes";
import NotFound from "./routes/notfound/NotFound";

ReactDOM.render(
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
                path={"*"}
                component={NotFound}
            />
        </Switch>
    </Router>
    ,
    document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals(console.log);
