import loadable from '@loadable/component';
import RouterModel from "../types/routes/RouterModel";

const routes: RouterModel[] = [
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
    }
];

export default routes;
