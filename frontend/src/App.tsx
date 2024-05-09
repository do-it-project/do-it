import { useEffect } from "react";
import { Button } from "./components/ui/button";

function App() {
  useEffect(() => {
    // Obtendo os parâmetros de consulta da URL
    const searchParams = new URLSearchParams(window.location.search);
    const email = searchParams.get("email");

    if (email) {
      // Faça o que quiser com o email aqui
      console.log(email);
    }
  }, []);

  return (
    <div className="h-screen w-full bg-black-1 flex justify-center items-center ">
      <div className="gap-4 flex flex-wrap">
        <p className="text-gray-1">Texto primário</p>
        <p className="text-gray-2">Texto secundário</p>
        <p className="text-gray-3">Texto alternativo</p>
        <p className="text-gray-4">Texto alternativo</p>
        <Button>primary blue</Button>
        <Button className="bg-blue-2">secondary blue</Button>
        <Button variant="destructive">error</Button>
        <div className="bg-black-2 w-44 text-gray-1">bg-2</div>
        <div className="bg-black-3 w-44 text-gray-1">bg-3</div>
        <div className="bg-black-4 w-44 text-gray-1">bg-4</div>
      </div>
    </div>
  );
}

export default App;
