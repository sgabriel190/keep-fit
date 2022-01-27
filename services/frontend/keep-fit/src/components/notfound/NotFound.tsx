import React from 'react';
import './NotFound.css';
import '../../shared/styles/shared.css';
import {Route} from "react-router-dom";
import {motion} from "framer-motion";

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
                    <motion.div
                        animate={{ y: 0, opacity: 1 }}
                        initial={{y: -100, opacity: 0}}
                        transition={{ ease: "easeOut", duration: 1 }}
                        className={"container-notfound container-main p-auto shadow"}>
                        <p className={"text-error font-weight-normal"}>Whoops! This page does not exist! :(</p>
                    </motion.div>
                </div>
            </Status>
        );
    }
}

export default NotFound;
