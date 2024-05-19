import { Route, Routes } from "react-router-dom";
import Layout from "./components/Layout";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Home from "./pages/student/Home";
import PhysicalAssessments from "./pages/student/PhysicalAssessments";
import Workouts from "./pages/student/Workouts";

function App() {
  return (
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
  );
}

export default App;
