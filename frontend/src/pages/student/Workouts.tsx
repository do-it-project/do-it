import CategorySection from "@/components/Workout/CategorySection";
import WCard from "@/components/Workout/WCard";
import {
  Select,
  SelectContent,
  SelectGroup,
  SelectItem,
  SelectLabel,
  SelectTrigger,
  SelectValue,
} from "@/components/ui/select";
import { RootState } from "@/store/store";
import { Workout } from "@/types/models";
import { useEffect, useState } from "react";
import { useSelector } from "react-redux";

const Workouts = () => {
  const userLogged = useSelector(
    (state: RootState) => state.userLogged.userLogged
  );

  const [categorySelected, setCategorySelected] = useState("todas");
  const [selectedWorkouts, setSelectedWorkouts] = useState<Workout[]>([]);
  const [groupedWorkouts, setGroupedWorkouts] = useState<any>({});

  const categories: any = {
    "TREINOS DE PEITO": "peito",
    "TREINOS DE COSTAS": "costa",
    "TREINOS DE PERNAS": "perna",
    "TREINOS DE PANTURRILHAS": "panturrilha",
    "TREINOS DE BICEPS": "biceps",
    "TREINOS DE TRICEPS": "triceps",
    "TREINOS DE OMBRO": "ombro",
    "TREINOS DE ABDÔMEN": "abdomen",
  };

  useEffect(() => {
    const workoutsByCategory: any = {};

    if (userLogged?.user?.workouts) {
      userLogged.user.workouts.forEach((workout) => {
        const workoutTag = workout.tag?.toLowerCase();
        for (const category in categories) {
          if (categories[category] === workoutTag) {
            if (!workoutsByCategory[category]) {
              workoutsByCategory[category] = [];
            }
            workoutsByCategory[category].push(workout);
          }
        }
      });
    }

    setGroupedWorkouts(workoutsByCategory);
  }, [userLogged]);

  return (
    <>
      <h1 className="text-blue-1 text-3xl mb-10 text-center">
        Visualize seus treinos e comece a fazer o trabalho duro!
      </h1>

      <h1 className="text-gray-1 mb-2 text-md ml-1 font-semibold">
        Filtrar por Categoria
      </h1>
      <Select
        defaultValue={categorySelected}
        onValueChange={(value) => {
          setCategorySelected(value);
          console.log(value);
        }}
      >
        <SelectTrigger className="w-[230px] capitalize">
          <SelectValue placeholder="Selecione uma categoria" />
        </SelectTrigger>
        <SelectContent>
          <SelectGroup>
            <SelectLabel>Categorias</SelectLabel>
            <SelectItem key={"todas"} value={"todas"}>
              Todas
            </SelectItem>
            {Object.keys(groupedWorkouts).map((category) => (
              <SelectItem
                key={category}
                value={category}
                className="capitalize"
              >
                {category}
              </SelectItem>
            ))}
          </SelectGroup>
        </SelectContent>
      </Select>

      {selectedWorkouts && (
        <div className="mt-10">
          <h2 className=" text-2xl mb-5 text-gray-2">Treinos atuais</h2>

          <div className="flex flex-wrap gap-10 justify-center lg:justify-normal mt-4">
            {selectedWorkouts.map((workout: Workout) => (
              <WCard
                workout={workout}
                key={workout.id}
                setSelectedWorkouts={setSelectedWorkouts}
                selectedWorkouts={selectedWorkouts}
                showCheckbox={false}
              />
            ))}
          </div>
        </div>
      )}

      {categorySelected === "todas"
        ? Object.keys(groupedWorkouts).map((category) => (
            <CategorySection
              category={category}
              key={category}
              groupedWorkouts={groupedWorkouts}
              selectedWorkouts={selectedWorkouts}
              setSelectedWorkouts={setSelectedWorkouts}
            />
          ))
        : groupedWorkouts[categorySelected] && (
            <CategorySection
              category={categorySelected}
              key={categorySelected}
              groupedWorkouts={groupedWorkouts}
              selectedWorkouts={selectedWorkouts}
              setSelectedWorkouts={setSelectedWorkouts}
            />
          )}
    </>
  );
};

export default Workouts;
