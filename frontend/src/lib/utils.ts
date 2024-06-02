import { type ClassValue, clsx } from "clsx";
import { toast } from "sonner";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const toastSuccessEditUser = (
  message: string,
  description?: string,
  label?: string
) =>
  toast.success(message, {
    description: description ?? new Date().toLocaleString(),
    action: {
      label: label ?? "Fechar",
      onClick: () => {},
    },
  });
