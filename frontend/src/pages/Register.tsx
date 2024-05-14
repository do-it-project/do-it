import { fetchCreateUserRequest } from "@/api/fetchCreateUserRequest";
import Logo from "@/assets/logo.png";
import NoPainNoGain from "@/assets/no-pain-no-gain.png";
import Input from "@/components/Input";
import LayoutSign from "@/components/LayoutSign";
import SpanError from "@/components/SpanError";
import { Button } from "@/components/ui/button";
import { CreateUserRequest } from "@/types";
import { yupResolver } from "@hookform/resolvers/yup";
import { AxiosError } from "axios";
import { useForm } from "react-hook-form";
import { BsFillTelephonePlusFill } from "react-icons/bs";
import { FaLock, FaUser } from "react-icons/fa";
import { ImProfile } from "react-icons/im";
import { Link } from "react-router-dom";
import * as yup from "yup";

const schema = yup.object().shape({
  email: yup.string().required("O email é obrigatório").email("Email inválido"),
  password: yup
    .string()
    .required("A senha é obrigatória")
    .min(5, "A senha deve conter no mínimo 5 dígitos"),
  name: yup
    .string()
    .required("O nome é obrigatório")
    .min(4, "O nome deve conter no mínimo 4 dígitos"),
  phone: yup.string().required("O telefone é obrigatório"),
});

const Register = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
  } = useForm({
    resolver: yupResolver(schema),
  });

  async function submitForm(data: CreateUserRequest) {
    data.type = "S";
    data.url_photo = "";

    await fetchCreateUserRequest(data)
      .then((res) => {
        console.log(res);
        reset();
      })
      .catch((err: AxiosError) => console.log(err.response?.data));
  }

  return (
    <LayoutSign>
      <div className="flex rounded-lg overflow-hidden m-6 transition-all duration-1000">
        <div className="flex flex-col items-center w-[450px] p-7 bg-black-2">
          <img className="w-[128px] h-[128px]" src={Logo} alt="logo image" />

          <h1 className="text-gray-1 text-[24px] font-semibold pt-4 pb-6 text-xl">
            Se tornar um parceiro <span className="text-blue-1">DO IT</span>
          </h1>

          <form
            className="flex flex-col w-full"
            onSubmit={handleSubmit(submitForm)}
          >
            <Input
              IconLeft={ImProfile}
              placeholder="Seu nome"
              type="text"
              register={{ ...register("name") }}
            />
            {errors.name?.message && (
              <SpanError message={errors.name?.message} />
            )}

            <Input
              IconLeft={FaUser}
              placeholder="E-mail"
              type="text"
              className="mt-2"
              register={{ ...register("email") }}
            />
            {errors.email?.message && (
              <SpanError message={errors.email?.message} />
            )}

            <Input
              IconLeft={BsFillTelephonePlusFill}
              placeholder="Seu telefone"
              type="text"
              className="mt-2"
              register={{ ...register("phone") }}
            />
            {errors.phone?.message && (
              <SpanError message={errors.phone?.message} />
            )}

            <Input
              IconLeft={FaLock}
              placeholder="Sua senha secreta"
              type="password"
              className="mt-2"
              register={{ ...register("password") }}
            />
            {errors.password?.message && (
              <SpanError message={errors.password?.message} />
            )}

            <Button
              className="rounded-lg p-6 my-4 text-gray-1 font-semibold text-base "
              type="submit"
            >
              Registrar
            </Button>
          </form>

          <div className="w-full flex items-center gap-2 text-gray-1 mt-10">
            <hr className="w-full text-blue-1" />
            ou
            <hr className="w-full text-blue-1" />
          </div>
          <Link
            to="/"
            className="text-gray-1 font-semibold cursor-pointer mt-2 transition hover:text-blue-1"
          >
            Entre com seus dados
          </Link>
        </div>

        <img
          className="max-w-[676px] hidden lg:block"
          src={NoPainNoGain}
          alt="no pain no gain image"
        />
      </div>
    </LayoutSign>
  );
};

export default Register;
