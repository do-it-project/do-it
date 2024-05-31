import { Route, Routes, useNavigate } from "react-router-dom";
import Layout from "./components/Layout";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/student/Home";
import PhysicalAssessments from "./pages/student/PhysicalAssessments";
import Workouts from "./pages/student/Workouts";
import { useDispatch } from "react-redux";
import { getInSession } from "./common/session";
import {
  resetUserLogged,
  setUserLogged,
} from "./store/userLogged/userLoggedSlice";
import { useEffect } from "react";
import { Toaster } from "./components/ui/sonner";

function App() {
  const dispatch = useDispatch();

  const navigate = useNavigate();

  useEffect(() => {
    const userInSession = getInSession("user");

    if (userInSession && JSON.parse(userInSession).user.type === "S") {
      navigate("/aluno/inicio");
      dispatch(setUserLogged(JSON.parse(userInSession)));
    } else if (userInSession && JSON.parse(userInSession).user.type === "P") {
      navigate("/personal/inicio");
    } else {
      dispatch(resetUserLogged());
      navigate("/");
    }
  }, []);

  return (
    <>
      <Toaster />
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/registrar" element={<Register />} />
        <Route path="/aluno/inicio" element={<Layout children={<Home />} />} />
        <Route
          path="/aluno/avaliacoes-fisicas"
          element={<Layout children={<PhysicalAssessments />} />}
        />
        <Route
          path="aluno/treinos"
          element={<Layout children={<Workouts />} />}
        />
      </Routes>
    </>
  );
}

export default App;
