import Dashboard from "./dashboard/Dashboard";
import Login from "./login/Login";
import Register from "./register/Register";
import RouterModel from "../types/RouterModel";
import Recipes from "./recipes/Recipes";
import MealPlan from "./mealplan/MealPlan";
import Welcome from "./welcome/Welcome";

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
    {
        url: "/mealplan",
        component: MealPlan,
        name:"My meal plan",
    },
    {
        url: "/",
        component: Welcome,
        name:"Welcome page",
    },
];

export default routes;
