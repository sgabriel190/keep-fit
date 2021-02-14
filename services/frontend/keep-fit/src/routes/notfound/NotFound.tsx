import React from 'react';
import './NotFound.css';

class NotFound extends React.Component<any, any>{
    render() {
        return (
            <div className={"container-notfound"}>
                <h1>Error 404! Page not found!</h1>
            </div>
        );
    }
}

export default NotFound;
