import store from "./store"

const selectJwtValue = () => store.getState().jwt

export default selectJwtValue;
