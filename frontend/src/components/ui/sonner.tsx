import { useTheme } from "next-themes";
import { Toaster as Sonner } from "sonner";

type ToasterProps = React.ComponentProps<typeof Sonner>;

const Toaster = ({ ...props }: ToasterProps) => {
  const { theme = "system" } = useTheme();

  return (
    <Sonner
      theme={theme as ToasterProps["theme"]}
      className="toaster group"
      toastOptions={{
        classNames: {
          toast:
            "group toast group-[.toaster]:bg-black-2 group-[.toaster]:text-gray-1 group-[.toaster]:border-gray-4/30 border group-[.toaster]:shadow-lg p-4",
          description: "group-[.toast]:text-muted-foreground",
          actionButton: "group-[.toast]:bg-gray-1 group-[.toast]:text-black-4",
          cancelButton: "group-[.toast]:bg-gray-1 group-[.toast]:text-black-4",
        },
      }}
      {...props}
    />
  );
};

export { Toaster };
