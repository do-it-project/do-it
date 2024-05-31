import { Token, User } from "./models";

export interface UserLogged {
  user: User;
  token: Token;
}

export interface LoginRequest {
  email: string;
  password: string;
}

export interface CreateUserRequest {
  name: string;
  phone: string;
  email: string;
  password: string;
  url_photo?: string;
  type?: "S" | "P";
}

export interface EditUserRequest {
  id?: number;
  name?: string;
  phone?: string;
  email?: string;
  password?: string;
  url_photo?: string;
}
