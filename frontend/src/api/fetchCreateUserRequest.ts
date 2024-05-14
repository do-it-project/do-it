import { CreateUserRequest } from "@/types";
import api from "./config";

export async function fetchCreateUserRequest(body: CreateUserRequest) {
  try {
    const res = await api.post("/users", body);
    return res.data;
  } catch (err) {
    throw err;
  }
}
