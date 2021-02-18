import loadable from '@loadable/component';
import RouterModel from "../types/RouterModel";
import Recipes from "../components/recipes/Recipes";
import MealPlan from "../components/mealplan/MealPlan";
import Welcome from "../components/welcome/Welcome";

const routes: RouterModel[] = [
    {
        url: "/dashboard",
        component: loadable(() => import("../components/dashboard/Dashboard")),
        name:"Dashboard",
    },
    {
        url: "/login",
        component: loadable(() => import("../components/login/Login")),
        name:"Login",
    },
    {
        url: "/register",
        component: loadable(() => import("../components/register/Register")),
        name:"Register",
    },
    {
        url: "/recipes",
        component: loadable(() => import("../components/recipes/Recipes")),
        name:"Recipes",
    },
    {
        url: "/mealplan",
        component: loadable(() => import("../components/mealplan/MealPlan")),
        name:"My meal plan",
    },
    {
        url: "/",
        component: loadable(() => import("../components/welcome/Welcome")),
        name:"Welcome page",
    },
];

export default routes;
