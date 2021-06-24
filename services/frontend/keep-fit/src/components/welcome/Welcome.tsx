import React from 'react';
import './Welcome.css';

class Welcome extends React.Component<any, any>{
    constructor(props: object) {
        super(props);
        this.state = {
            data: null
        }
    }

    async componentDidMount(){
    }

    render() {
        return (
            <div className={"container-welcome"}>
                <h1>Welcome works!</h1>
            </div>
        );
    }
}

export default Welcome;
