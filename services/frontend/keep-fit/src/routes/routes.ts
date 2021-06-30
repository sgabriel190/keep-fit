import loadable from '@loadable/component';
import RouterModel from "../types/routes/RouterModel";

const routes: RouterModel[] = [
    {
        url: "/recipes",
        component: loadable(() => import("../components/recipes/Recipes")),
    },
    {
        url: "/createPlan",
        component: loadable(() => import("../components/userPlan/createUserPlan/CreateUserPlan")),
    },
    {
        url: "/user/details",
        component: loadable(() => import("../components/user-details/UserDetails")),
    },
    {
        url: "/plan",
        component: loadable(() => import("../components/userPlan/MealPlan")),
    },
    {
        url: "/profile",
        component: loadable(() => import("../components/profile/Profile")),
    },
    {
        url: "/recipe/:recipeId",
        component: loadable(() => import("../components/recipes/Recipe/Recipe")),
    }
];

export default routes;
