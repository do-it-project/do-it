import { fetchLoginRequest } from "@/api/fetchLoginRequest";
import Logo from "@/assets/logo.png";
import NoPainNoGain from "@/assets/no-pain-no-gain.png";
import { storeInSession } from "@/common/session";
import Input from "@/components/Input";
import LayoutSign from "@/components/LayoutSign";
import SpanError from "@/components/SpanError";
import { Button } from "@/components/ui/button";
import { AppDispatch } from "@/store/store";
import { setUserLogged } from "@/store/userLogged/userLoggedSlice";
import { LoginRequest, UserLogged } from "@/types";
import { yupResolver } from "@hookform/resolvers/yup";
import { useForm } from "react-hook-form";
import { FaLock, FaUser } from "react-icons/fa";
import { useDispatch } from "react-redux";
import { Link, useNavigate } from "react-router-dom";
import * as yup from "yup";

const schema = yup.object().shape({
  email: yup.string().required("O email é obrigatório").email("Email inválido"),
  password: yup
    .string()
    .required("A senha é obrigatória")
    .min(5, "A senha deve conter no mínimo 5 dígitos"),
});

const Login = () => {
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm({
    resolver: yupResolver(schema),
  });

  const navigate = useNavigate();

  const dispatch: AppDispatch = useDispatch();

  async function submitForm(data: LoginRequest) {
    try {
      const res: UserLogged = await fetchLoginRequest(data);

      storeInSession("user", JSON.stringify(res));
      dispatch(setUserLogged(res));
      console.log(res);

      navigate("/aluno/inicio");
    } catch (err) {
      console.log(err);
    }
  }

  return (
    <LayoutSign>
      <div className="flex rounded-lg overflow-hidden">
        <img
          className="max-w-[590px] hidden lg:block"
          src={NoPainNoGain}
          alt="work harder image"
        />

        <div className="flex flex-col items-center max-w-[420px] md:w-svw p-7  bg-black-2">
          <img
            className="w-[100px] h-[100px] md:w-[128px] md:h-[128px]"
            src={Logo}
            alt="logo image"
          />

          <h1 className="text-gray-1 text-[24px] font-semibold pt-4 pb-6 text-xl">
            Entre com seus dados
          </h1>

          <form
            className="flex flex-col w-full"
            onSubmit={handleSubmit(submitForm)}
          >
            <Input
              IconLeft={FaUser}
              placeholder="E-mail"
              type="text"
              register={{ ...register("email") }}
            />
            {errors.email?.message && (
              <SpanError message={errors.email?.message} />
            )}

            <Input
              IconLeft={FaLock}
              placeholder="Sua senha secreta"
              type="password"
              className="mt-3"
              register={{ ...register("password") }}
            />
            {errors.password?.message && (
              <SpanError message={errors.password?.message} />
            )}

            <Button
              className="rounded-lg p-6 my-4 text-gray-1 font-semibold text-base "
              type="submit"
            >
              Entrar
            </Button>
          </form>

          <span className="text-gray-1 font-semibold cursor-pointer self-start text-sm transition hover:text-blue-1">
            Esqueci minha senha
          </span>

          <div className="w-full flex items-center gap-2 text-gray-1 mt-10">
            <hr className="w-full text-blue-1" />
            ou
            <hr className="w-full text-blue-1" />
          </div>
          <Link
            to="/registrar"
            className="text-gray-1 font-semibold cursor-pointer mt-2 transition hover:text-blue-1"
          >
            Se tornar um parceiro <span className="text-blue-1">DO IT</span>
          </Link>
        </div>
      </div>
    </LayoutSign>
  );
};

export default Login;
