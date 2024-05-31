import { UserLogged } from "@/types";
import { PayloadAction, createSlice } from "@reduxjs/toolkit";

type UserLoggedState = {
  userLogged: UserLogged | null;
  isUserLogged: boolean;
};

const initialState: UserLoggedState = {
  isUserLogged: false,
  userLogged: null,
};

export const userLoggedSlice = createSlice({
  name: "userLogged",
  initialState,
  reducers: {
    toggleIsUserLogged: (state) => {
      state.isUserLogged = !state.isUserLogged;
    },

    setUserLogged: (state, action: PayloadAction<UserLogged>) => {
      state.userLogged = action.payload;
      state.isUserLogged = true;
    },

    resetUserLogged: (state) => {
      state.userLogged = initialState.userLogged;
      state.isUserLogged = false;
    },
  },
});

export const { toggleIsUserLogged, setUserLogged, resetUserLogged } =
  userLoggedSlice.actions;
export default userLoggedSlice.reducer;
