import UserDetailsModel from "./UserDetailsModel";

type UserProfileModel = {
    id: number;
    username: string;
    email: string;
    userDetails: UserDetailsModel;
};

export default UserProfileModel;
