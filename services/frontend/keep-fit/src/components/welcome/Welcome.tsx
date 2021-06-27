import React from 'react';
import './Welcome.css';
import '../../shared/styles/shared.css';
import {motion} from "framer-motion";

class Welcome extends React.Component<any, any>{

    render() {
        return (
            <div className={"container-custom"}>
                <motion.div
                    animate={{ y: 0, opacity: 1 }}
                    initial={{y: -100, opacity: 0}}
                    transition={{ ease: "easeOut", duration: 1 }}
                    className={"container-main shadow container-welcome container-margin"}>
                    <div className={"container-title"}>
                        <img
                            alt=""
                            src="/android-chrome-192x192.png"
                            className=""
                        />
                        <div className={"title-container"}>
                            <h2 className={"text-title"}>Welcome to Keep fit!</h2>
                            <p className={"subtitle-text"}>Keep track of your meals and maintain a healthy lifestyle!</p>
                        </div>
                    </div>
                    <div className={"container-content"}>
                        <div className={"text-card container-content"}>
                            <p className={"content-text-title"}>What is Keep Fit?</p>
                            <p className={"text-box"}>Keep fit is a web application created as a tool for people to monitor their food intake and create a menu plan.
                                This can be a useful tool for people who want to lose/gain/maintain their weight.</p>
                        </div>
                        <div className={"text-card container-content"}>
                            <p className={"content-text-title"}>How should you use the app?</p>
                            <p className={"text-box"}>The flow of the app is a basic one. All you need to do is register with your new account credentials.
                                After you successfully registered you need to login to your account and provide your details: gender,
                                height, weight, etc.</p>
                        </div>
                        <div className={"text-card container-content"}>
                            <p className={"content-text-title"}>What are the benefits from using the app?</p>
                            <p className={"text-box"}>After you've successfully registered and logged in, you can search the recipe database
                                which contains up to 8000 recipes. The website computed your BMI, water need per day(WND), your ideal weight
                                and your calorie intake based on your information.</p>
                        </div>
                        <div className={"text-card container-content"}>
                            <p className={"content-text-title"}>Can I search for recipes myself?</p>
                            <p className={"text-box"}>Yes. You can search into all the recipes and pick yourself what do you want to eat for today.</p>
                        </div>
                        <div className={"text-card container-content"}>
                            <p className={"content-text-title"}>How do I setup my plan?</p>
                            <p className={"text-box"}>For your plan to be setup you need to provide your details first. The application
                                then computes your normal indexes. Based on your gender and activity the plan will contain calories between
                                your calorie index intake +- 100. The meals should not be strict as you should consume exactly X calories.</p>
                        </div>
                    </div>
                </motion.div>
            </div>
        );
    }
}

export default Welcome;
