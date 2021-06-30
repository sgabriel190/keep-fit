import UserDetailsModel from "../nutrition/UserDetailsModel";

type UserProfileModel = {
    id: number;
    username: string;
    email: string;
    userDetails: UserDetailsModel;
};

export default UserProfileModel;
