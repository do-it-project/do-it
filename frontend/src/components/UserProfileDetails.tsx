import Frabizzyz from "@/assets/image 2.png";
import { Avatar, AvatarFallback, AvatarImage } from "./ui/avatar";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
  DialogTrigger,
} from "./ui/dialog";
import { Button } from "./ui/button";
import Input from "./Input";
import { BsFillTelephonePlusFill } from "react-icons/bs";
import { FaLock, FaUser } from "react-icons/fa";
import { ImProfile } from "react-icons/im";

const UserProfileDetails = () => {
  return (
    <Dialog>
      <DialogTrigger asChild>
        <div className="flex gap-4 absolute lg:right-8 md:right-6 right-2 top-6 items-center text-gray-1 py-3 px-4 rounded-lg cursor-pointer transition-colors duration-300 hover:bg-black-2/90">
          <Avatar className="w-12 h-12">
            <AvatarImage src={Frabizzyz} />
            <AvatarFallback>CN</AvatarFallback>
          </Avatar>
          <div className="hidden md:block">
            <h2>Fabrizzyz</h2>
            <h3 className="text-sm text-gray-2">v.marins@aluno.ifsp.edu.br</h3>
          </div>
        </div>
      </DialogTrigger>
      <DialogContent className="sm:max-w-[425px]">
        <DialogHeader className="text-gray-1">
          <DialogTitle>Edição de perfil</DialogTitle>
          <DialogDescription>
            Faça mudanças nos seus dados aqui. Clique em salvar quando terminar.
          </DialogDescription>
        </DialogHeader>
        <form
          className="flex flex-col w-full "
          onSubmit={(e) => {
            e.preventDefault();
            console.log("enviei");
          }}
        >
          <Input IconLeft={ImProfile} placeholder="Seu nome" type="text" />

          <Input
            IconLeft={FaUser}
            placeholder="E-mail"
            type="text"
            disabled={true}
            className="mt-2"
          />

          <Input
            IconLeft={BsFillTelephonePlusFill}
            placeholder="Seu telefone"
            type="text"
            className="mt-2"
          />

          <Input
            IconLeft={FaLock}
            placeholder="Sua senha secreta"
            type="password"
            className="mt-2"
          />

          <DialogFooter className="mt-4">
            <Button type="submit">Salvar</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default UserProfileDetails;
