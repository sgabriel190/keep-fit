import Dashboard from "./dashboard/Dashboard";
import Login from "./login/Login";
import Register from "./register/Register";
import RouterModel from "../types/RouterModel";
import Recipes from "./recipes/Recipes";

const routes: RouterModel[] = [
    {
        url: "/dashboard",
        component: Dashboard,
        name:"Dashboard",
    },
    {
        url: "/login",
        component: Login,
        name:"Login",
    },
    {
        url: "/register",
        component: Register,
        name:"Register",
    },
    {
        url: "/recipes",
        component: Recipes,
        name:"Recipes",
    },
];

export default routes;
