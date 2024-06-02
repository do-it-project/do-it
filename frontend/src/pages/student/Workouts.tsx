import WCard from "@/components/Workout/WCard";
import { RootState } from "@/store/store";
import { useSelector } from "react-redux";

const Workouts = () => {
  const userLogged = useSelector(
    (state: RootState) => state.userLogged.userLogged
  );

  return (
    <>
      <h1 className="text-gray-1 text-3xl mb-10">
        Visualize seus treinos e comece a fazer o trabalho duro!
      </h1>

      <div className="flex flex-wrap gap-10 justify-center lg:justify-normal">
        {userLogged?.user?.workouts?.map((workout) => (
          <WCard
            key={workout.id}
            description={workout.comments}
            name={workout.name.toUpperCase()}
            date={workout.creationDate}
            workout_exercises={workout.workout_exercises}
          />
        ))}
      </div>
    </>
  );
};

export default Workouts;
