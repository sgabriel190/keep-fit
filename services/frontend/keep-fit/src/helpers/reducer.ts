const initialState = { jwt: "" };

const jwtReducer = (state = initialState, action: any) => {
    if(action.type === "login"){
        return {
            jwt: action.payload
        }
    }
    return state;
}

export default jwtReducer;
