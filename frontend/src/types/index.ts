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
