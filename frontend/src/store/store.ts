import { configureStore } from "@reduxjs/toolkit";
import userLoggedSlice from "./userLogged/userLoggedSlice";

export const store = configureStore({
  reducer: {
    userLogged: userLoggedSlice,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
