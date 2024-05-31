import { EditUserRequest } from "@/types";
import api from "./config";

export async function fetchEditUserRequest(body: EditUserRequest) {
  try {
    const res = await api.put("/users", body);
    return res.data;
  } catch (err) {
    throw err;
  }
}
