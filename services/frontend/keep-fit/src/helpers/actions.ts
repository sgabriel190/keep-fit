const addJwt = (jwt: string) => {
    return {
        type: "login",
        payload: jwt
    }
}

export default addJwt;
