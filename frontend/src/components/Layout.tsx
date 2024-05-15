import { useState } from "react";
import { Outlet } from "react-router-dom";
import Navbar from "./Navbar";
import { Switch } from "./ui/switch";

const Layout = () => {
  const [toggleMenu, setToggleMenu] = useState(false);
  return (
    <div className="w-full bg-black-1 h-screen">
      <Navbar toggleMenu={toggleMenu} />
      <div className="md:ml-[328px] h-full py-5 pr-6">
        <Outlet />
      </div>

      <Switch
        className={"absolute right-6 bottom-6 text-gray-1 md:hidden z-10"}
        onClick={() => setToggleMenu((prev) => !prev)}
      />
    </div>
  );
};

export default Layout;
