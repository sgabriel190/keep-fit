import { createStore} from "@reduxjs/toolkit";
import jwtReducer from "./reducer";

const store = createStore(jwtReducer);

export default store;
