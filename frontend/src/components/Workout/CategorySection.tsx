import { Workout } from "@/types/models";
import WCard from "./WCard";

interface CategorySectionProps {
  category: string;
  groupedWorkouts: any;
  selectedWorkouts?: Workout[];
  setSelectedWorkouts?: React.Dispatch<React.SetStateAction<Workout[]>>;
}

const CategorySection = ({
  category,
  groupedWorkouts,
  selectedWorkouts,
  setSelectedWorkouts,
}: CategorySectionProps) => {
  return (
    <div className="mt-10">
      <h2 className=" text-2xl mb-5 text-gray-2">{category}</h2>

      <div className="flex flex-wrap gap-10 justify-center lg:justify-normal mt-4">
        {groupedWorkouts[category].map((workout: Workout) => (
          <WCard
            workout={workout}
            key={workout.id}
            selectedWorkouts={selectedWorkouts}
            setSelectedWorkouts={setSelectedWorkouts}
          />
        ))}
      </div>
    </div>
  );
};

export default CategorySection;
