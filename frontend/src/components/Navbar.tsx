import Logo from "@/assets/logo.png";
import NavItem from "@/components/NavItem";
import { useState } from "react";
import { FaClipboardList, FaDumbbell, FaHome } from "react-icons/fa";
import { useNavigate } from "react-router-dom";

const Navbar = ({ toggleMenu }: any) => {
  const [activeItem, setActiveItem] = useState("Início");
  const navigate = useNavigate();

  return (
    <nav
      className={`bg-black-2 fixed left-0 max-w-[300px] h-full w-full px-3 p-10 overflow-hidden transition duration-300 z-20
      ${toggleMenu ? "translate-x-0" : "-translate-x-[150%] md:translate-x-0"}
    `}
    >
      <img src={Logo} alt="Logo doit" className="w-[120px] h-[120px] mx-auto" />

      <h1 className="text-[32px] text-gray-1 flex justify-center gap-2 font-bold my-4">
        YOU CAN <span className="text-blue-1">DO IT</span>
      </h1>

      <div className="h-full w-full flex flex-col mt-36 ">
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
    </nav>
  );
};

export default Navbar;
