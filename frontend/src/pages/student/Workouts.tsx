import WCard from "@/components/Workout/WCard";
import { Separator } from "@/components/ui/separator";
import { RootState } from "@/store/store";
import { Workout } from "@/types/models";
import { useSelector } from "react-redux";

const Workouts = () => {
  const userLogged = useSelector(
    (state: RootState) => state.userLogged.userLogged
  );

  const categories: any = {
    "TREINOS DE PEITO": "peito",
    "TREINOS DE COSTAS": "costa",
    "TREINOS DE PERNAS": "perna",
    // Adicione outras categorias conforme necessário
  };

  const groupedWorkouts: any = {};

  if (userLogged?.user?.workouts) {
    userLogged.user.workouts.forEach((workout) => {
      const workoutTag = workout.tag?.toLowerCase();
      for (const category in categories) {
        if (categories[category] === workoutTag) {
          if (!groupedWorkouts[category]) {
            groupedWorkouts[category] = [];
          }
          groupedWorkouts[category].push(workout);
        }
      }
    });
  }

  return (
    <>
      <h1 className="text-gray-1 text-3xl mb-10">
        Visualize seus treinos e comece a fazer o trabalho duro!
      </h1>

      {Object.keys(groupedWorkouts).map((category) => (
        <>
          <div key={category} className="mt-10">
            <h2 className=" text-2xl mb-3 text-gray-1">{category}</h2>

            <div className="flex flex-wrap gap-10 justify-center lg:justify-normal mt-4">
              {groupedWorkouts[category].map((workout: Workout) => (
                <WCard
                  key={workout.id}
                  description={workout.comments}
                  name={workout.name.toUpperCase()}
                  date={workout.creationDate}
                  workout_exercises={workout.workout_exercises}
                />
              ))}
            </div>
          </div>
        </>
      ))}
    </>
  );
};

export default Workouts;
