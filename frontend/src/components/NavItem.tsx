import { Button } from "./ui/button";

type NavItemsProps = {
  text: string;
  Icon: React.ElementType;
  isActive?: boolean;
  onClick: () => void;
  className?: string;
};

const NavItem = ({
  text,
  Icon,
  isActive,
  onClick,
  className,
}: NavItemsProps) => {
  return (
    <div
      onClick={onClick}
      className={`${className} flex items-center w-full my-1 p-2 rounded-lg cursor-pointer transition-colors duration-300 hover:bg-black-1/90 text-gray-1 ${
        isActive ? "bg-black-1/90 border-l-blue-1 border-l-2" : ""
      }`}
    >
      <Icon className="w-[26px] h-[26px] transition" />
      <Button variant="ghost" className={`text-xl`}>
        {text}
      </Button>
    </div>
  );
};

export default NavItem;
