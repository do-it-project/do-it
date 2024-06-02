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
import { useDispatch, useSelector } from "react-redux";
import { RootState } from "@/store/store";
import * as yup from "yup";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import SpanError from "./SpanError";
import { fetchEditUserRequest } from "@/api/fetchEditUserRequest";
import { AxiosError } from "axios";
import { EditUserRequest, UserLogged } from "@/types";
import { toastSuccessEditUser } from "@/lib/utils";
import { Token, User } from "@/types/models";
import { storeInSession } from "@/common/session";
import { setUserLogged } from "@/store/userLogged/userLoggedSlice";

const schema = yup.object().shape({
  name: yup.string().min(4, "O nome deve conter no mínimo 4 caracteres"),
  phone: yup.string().min(10, "Telefone inválido"),
  password: yup.string().test({
    test: function (value) {
      if (!value || value.trim() === "") {
        return true;
      }
      return value.length >= 5;
    },
    message: "A senha deve conter no mínimo 5 caracteres",
  }),
  confirmPassword: yup
    .string()
    .oneOf([yup.ref("password")], "Senhas não coincidem"),
});

const UserProfileDetails = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({ resolver: yupResolver(schema) });

  const userLogged = useSelector(
    (state: RootState) => state.userLogged.userLogged
  );

  const dispatch = useDispatch();

  const handleUpdateUserDetails = async (data: any) => {
    const request: EditUserRequest = {
      id: userLogged?.user?.id,
      name: data.name,
      phone: data.phone,
      email: userLogged?.user?.email,
      password: data.password ?? userLogged?.user?.password,
      url_photo: userLogged?.user?.url_photo,
    };

    await fetchEditUserRequest(request)
      .then((res: User) => {
        const UserLoggedUpdated: UserLogged = {
          user: {
            ...userLogged?.user,
            type: res.type,
            email: res.email,
            id: res.id,
            name: res.name,
            phone: res.phone,
          },
          token: userLogged?.token as Token,
        };

        dispatch(setUserLogged(UserLoggedUpdated));
        storeInSession("user", JSON.stringify(UserLoggedUpdated));

        toastSuccessEditUser("Perfil alterado com sucesso!");
        reset();
      })
      .catch((err: AxiosError) => console.log(err.response?.data));
  };

  return (
    <Dialog onOpenChange={() => reset()}>
      <DialogTrigger asChild>
        <div className="flex gap-4 absolute lg:right-8 md:right-6 right-2 top-6 items-center text-gray-1 py-3 px-3 rounded-lg cursor-pointer transition-colors duration-300 hover:bg-black-2/90">
          <Avatar className="w-12 h-12">
            <AvatarImage src={Frabizzyz} />
            <AvatarFallback>CN</AvatarFallback>
          </Avatar>
          <div className="hidden md:block">
            <h2>{userLogged?.user?.name.split(" ")[0]}</h2>
            <h3 className="text-sm text-gray-2">{userLogged?.user?.email}</h3>
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
          className="flex flex-col w-full"
          onSubmit={handleSubmit(handleUpdateUserDetails)}
        >
          <Input
            IconLeft={ImProfile}
            placeholder="Seu nome"
            type="text"
            defaultValue={userLogged?.user?.name}
            register={{ ...register("name") }}
          />
          {errors.name?.message && <SpanError message={errors.name.message} />}

          <Input
            IconLeft={FaUser}
            placeholder={userLogged?.user?.email as string}
            type="text"
            disabled={true}
            className="mt-2"
          />

          <Input
            IconLeft={BsFillTelephonePlusFill}
            placeholder="Seu telefone"
            type="text"
            className="mt-2"
            defaultValue={userLogged?.user?.phone}
            register={{ ...register("phone") }}
          />
          {errors.phone?.message && (
            <SpanError message={errors.phone.message} />
          )}

          <Input
            IconLeft={FaLock}
            placeholder="Sua senha secreta"
            type="password"
            className="mt-2"
            register={{ ...register("password") }}
          />
          {errors.password?.message && (
            <SpanError message={errors.password.message} />
          )}

          <Input
            IconLeft={FaLock}
            placeholder="Confirma sua senha secreta"
            type="password"
            className="mt-2"
            register={{ ...register("confirmPassword") }}
          />
          {errors.confirmPassword?.message && (
            <SpanError message={errors.confirmPassword.message} />
          )}

          <DialogFooter className="mt-4">
            <Button type="submit">Salvar</Button>
          </DialogFooter>
        </form>
      </DialogContent>
    </Dialog>
  );
};

export default UserProfileDetails;
