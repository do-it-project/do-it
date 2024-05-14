import { LoginRequest } from "@/types";
import api from "./config";

export async function fetchLoginRequest(body: LoginRequest) {
  try {
    const res = await api.post("/users/login", body);
    return res.data;
  } catch (err) {
    throw err;
  }
}
