import { useState } from "react";
import Navbar from "./Navbar";
import { Switch } from "./ui/switch";
import UserProfileDetails from "./UserProfileDetails";

type PropsType = {
  children: React.ReactNode;
};

const Layout = ({ children }: PropsType) => {
  const [toggleMenu, setToggleMenu] = useState(false);
  return (
    <div className="w-full bg-black-1 pb-20 min-h-screen">
      <Navbar toggleMenu={toggleMenu} />

      <UserProfileDetails />

      <div className="md:ml-[328px] ml-6 h-full pt-28 pr-6">
        <hr className="text-gray-1 h-2 mb-4"></hr>
        {children}
      </div>

      <Switch
        className={"absolute right-6 bottom-6 text-gray-1 md:hidden z-10"}
        onClick={() => setToggleMenu((prev) => !prev)}
      />
    </div>
  );
};

export default Layout;
