import { type ClassValue, clsx } from "clsx";
import { toast } from "sonner";
import { twMerge } from "tailwind-merge";

export function cn(...inputs: ClassValue[]) {
  return twMerge(clsx(inputs));
}

export const toastSuccessEditUser = () =>
  toast("Perfil atualizado com sucesso!", {
    description: Date.now().toLocaleString().toString(),
    action: {
      label: "Fechar",
      onClick: () => {},
    },
  });
