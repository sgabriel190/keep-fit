import React from 'react';
import './NotFound.css';
import {Route} from "react-router-dom";

class Status extends React.Component<any, any>{
    render(){
        return(
            <Route
                render={({staticContext}) => {
                    if(staticContext) staticContext.statusCode = this.props.code;
                    return this.props.children;
                }}
            />
        );
    }
}

class NotFound extends React.Component<any, any>{
    render() {
        return (
            <Status
                code={404}
            >
                <div className={"container-custom"}>
                    <div className={"container-notfound p-auto shadow"}>
                        <p className={"text-error font-weight-normal"}>Whoops! This page does not exist! :(</p>
                    </div>
                </div>
            </Status>
        );
    }
}

export default NotFound;
