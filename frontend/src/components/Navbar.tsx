import Logo from "@/assets/logo.png";
import { removeFromSession } from "@/common/session";
import NavItem from "@/components/NavItem";
import { resetUserLogged } from "@/store/userLogged/userLoggedSlice";
import { useState } from "react";
import { FaClipboardList, FaDumbbell, FaHome } from "react-icons/fa";
import { IoMdLogOut } from "react-icons/io";
import { useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";

const Navbar = ({ toggleMenu }: any) => {
  const [activeItem, setActiveItem] = useState("Início");
  const navigate = useNavigate();

  const dispatch = useDispatch();

  return (
    <nav
      className={`bg-black-2 fixed left-0 max-w-[300px] h-full w-full px-3 py-10 overflow-hidden transition duration-300 z-20
      ${toggleMenu ? "translate-x-0" : "-translate-x-[150%] md:translate-x-0"}
    `}
    >
      <img src={Logo} alt="Logo doit" className="w-[120px] h-[120px] mx-auto" />

      <h1 className="text-[32px] text-gray-1 flex justify-center gap-2 font-bold my-4">
        YOU CAN <span className="text-blue-1">DO IT</span>
      </h1>

      <div className="md:h-full max-h-[80%] w-full flex flex-col justify-between mt-10 md:gap-0 ">
        <div>
          <NavItem
            Icon={FaHome}
            text="Início"
            isActive={activeItem === "Início"}
            onClick={() => {
              setActiveItem("Início");
              navigate("/aluno/inicio");
            }}
          />
          <NavItem
            Icon={FaClipboardList}
            text="Avaliações Físicas"
            isActive={activeItem === "Avaliações Físicas"}
            onClick={() => {
              setActiveItem("Avaliações Físicas");
              navigate("/aluno/avaliacoes-fisicas");
            }}
          />
          <NavItem
            Icon={FaDumbbell}
            text="Meus treinos"
            isActive={activeItem === "Meus treinos"}
            onClick={() => {
              setActiveItem("Meus treinos");
              navigate("/aluno/treinos");
            }}
          />
        </div>

        <NavItem
          className="mb-10"
          Icon={IoMdLogOut}
          text="Sair"
          onClick={() => {
            dispatch(resetUserLogged());
            removeFromSession("user");
            navigate("/");
          }}
        />
      </div>
    </nav>
  );
};

export default Navbar;
