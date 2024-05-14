import { PayloadAction, createSlice } from "@reduxjs/toolkit";

export type User = {
  name: string;
  age: number;
  email: string;
  password: string;
};

type UserLoggedState = {
  userLogged: User | null;
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

    setUserLogged: (state, action: PayloadAction<User>) => {
      state.userLogged = action.payload;
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
