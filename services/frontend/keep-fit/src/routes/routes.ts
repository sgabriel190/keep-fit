import loadable from '@loadable/component';
import RouterModel from "../types/routes/RouterModel";

const routes: RouterModel[] = [
    {
        url: "/dashboard",
        component: loadable(() => import("../components/dashboard/Dashboard")),
    },
    {
        url: "/login",
        component: loadable(() => import("../components/login/Login")),
    },
    {
        url: "/register",
        component: loadable(() => import("../components/register/Register")),
    },
    {
        url: "/recipes",
        component: loadable(() => import("../components/recipes/Recipes")),
    },
    {
        url: "/plan",
        component: loadable(() => import("../components/mealplan/MealPlan")),
    },
    {
        url: "/profile",
        component: loadable(() => import("../components/profile/Profile")),
    },
    {
        url: "/",
        component: loadable(() => import("../components/welcome/Welcome")),
    },
];

export default routes;
