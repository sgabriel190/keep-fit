import ActivityTypeModel from "./ActivityTypeModel";
import GenderModel from "./GenderModel";
import DietTypeModel from "./DietTypeModel";

type UserDetailsModel = {
    age: number;
    height: number;
    weight: number;
    idealWeight: number;
    calories: number;
    bmi: number;
    wnd: number;
    activityType: ActivityTypeModel;
    gender: GenderModel;
    dietType: DietTypeModel;
};

export default UserDetailsModel;
