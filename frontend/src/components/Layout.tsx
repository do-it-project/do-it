import { useState } from "react";
import Navbar from "./Navbar";
import { Switch } from "./ui/switch";
import UserProfileDetails from "./UserProfileDetails";
import { TiThMenu } from "react-icons/ti";

type PropsType = {
  children: React.ReactNode;
};

const Layout = ({ children }: PropsType) => {
  const [isMenuMobileOpen, setIsMenuMobileOpen] = useState(false);

  return (
    <div className="w-full bg-black-1 pb-20 min-h-screen">
      <Navbar
        toggleMenu={isMenuMobileOpen}
        handleCloseMenuMobile={() => setIsMenuMobileOpen(false)}
      />

      <div className="md:ml-[320px] ml-6 mr-6">
        <div className="h-full py-5 flex justify-between items-center">
          <TiThMenu
            className="size-7 transition text-gray-1 md:hidden z-10 cursor-pointer"
            onClick={() => setIsMenuMobileOpen((prev) => !prev)}
          />

          <UserProfileDetails />
        </div>

        <hr className="text-gray-4 h-2" />

        <div className="mt-5">{children}</div>
      </div>
    </div>
  );
};

export default Layout;
