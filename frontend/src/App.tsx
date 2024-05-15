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
      <Route path="/dash" element={<Layout />}>
        <Route path="inicio" element={<Home />} />
        <Route path="physicalAssessments" element={<PhysicalAssessments />} />
        <Route path="workouts" element={<Workouts />} />
      </Route>
    </Routes>
  );
}

export default App;
