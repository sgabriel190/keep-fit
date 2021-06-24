import {configureStore} from "@reduxjs/toolkit";
import jwtReducer from "./reducer";

const store = configureStore({reducer: jwtReducer});

export default store;
