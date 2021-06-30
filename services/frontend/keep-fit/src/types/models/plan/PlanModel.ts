import MenuModel from "./MenuModel";

type PlanModel = {
    idUser: number;
    planDays: number;
    description: string;
    menus: MenuModel[];
}

export default PlanModel;
