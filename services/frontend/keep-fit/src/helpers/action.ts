const addJwt = {
    type: "login",
    payload: sessionStorage.getItem("jwt")
}

export default addJwt;
