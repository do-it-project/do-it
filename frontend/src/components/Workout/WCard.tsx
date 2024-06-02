import {
  Card,
  CardContent,
  CardDescription,
  CardFooter,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import { FaDumbbell } from "react-icons/fa";
import { FaDumbbell as FaDumbbell2 } from "react-icons/fa6";
import { LuDumbbell } from "react-icons/lu";
import { BiDumbbell } from "react-icons/bi";
import { LiaDumbbellSolid } from "react-icons/lia";
import { LuEye } from "react-icons/lu";
import { GiGymBag } from "react-icons/gi";
import { CgGym } from "react-icons/cg";
import {
  Tooltip,
  TooltipContent,
  TooltipProvider,
  TooltipTrigger,
} from "../ui/tooltip";
import { DialogTrigger } from "@radix-ui/react-dialog";
import {
  Dialog,
  DialogContent,
  DialogDescription,
  DialogFooter,
  DialogHeader,
  DialogTitle,
} from "../ui/dialog";
import { Button } from "../ui/button";
import { WorkoutExercise } from "@/types/models";

const workouts_images = [
  <FaDumbbell />,
  <FaDumbbell2 />,
  <LuDumbbell />,
  <BiDumbbell />,
  <LiaDumbbellSolid />,
  <GiGymBag />,
  <CgGym />,
];

interface WCardProps {
  name: string;
  description: string;
  date?: string;
  classname?: string;
  workout_exercises?: WorkoutExercise[];
}

const WCard = (props: WCardProps) => {
  return (
    <Card
      className={`${props.classname} w-[300px] border-none drop-shadow-xl   shadow-lg shadow-black-4`}
    >
      <CardHeader>
        <CardTitle className="text-lg text-blue-1 font-bold flex justify-between mb-2 whitespace-break-spaces ">
          <h1>{props.name}</h1>

          <TooltipProvider>
            <Tooltip>
              <TooltipTrigger className="mb-auto">
                <Dialog>
                  <DialogTrigger asChild>
                    <LuEye className="text-blue-1 cursor-pointer transition-colors hover:text-blue-2 duration-400 hover:scale-[1.1] active:scale-[1.0] h-15 w-15 mt-1 text-xl" />
                  </DialogTrigger>

                  <DialogContent className="max-w-[400px] max-h-[400px] lg:max-h-full sm:max-w-[450px] overflow-scroll md:overflow-auto">
                    <DialogHeader>
                      <DialogTitle className="text-blue-1">
                        {props.name}
                      </DialogTitle>
                    </DialogHeader>

                    <div className="my-3 text-gray-1">
                      {props.workout_exercises?.map((we) => (
                        <div className="flex justify-between items-center bg-black-4/70 p-3 rounded-md my-2">
                          <h3 className="capitalize">{we.exercise.name}</h3>
                          <div className="flex gap-3 items-center">
                            <span>
                              {we.sets}x{we.repetitions} - {we.rest_pause}
                            </span>
                            <LuEye className="text-blue-1 cursor-pointer transition-colors hover:text-blue-2 duration-400 hover:scale-[1.1] active:scale-[1.0] h-15 w-15 text-xl" />
                          </div>
                        </div>
                      ))}
                    </div>
                  </DialogContent>
                </Dialog>
              </TooltipTrigger>
              <TooltipContent> Visualizar </TooltipContent>
            </Tooltip>
          </TooltipProvider>
        </CardTitle>

        <CardDescription className="text-md whitespace-break-spaces">
          {props.description}
        </CardDescription>
      </CardHeader>

      <CardContent>
        <div className="w-full text-[5rem] flex justify-center items-center mb-14">
          {workouts_images[Math.floor(Math.random() * 5)]}
        </div>
      </CardContent>

      <CardFooter className="flex justify-between text-sm text-gray-3 font-medium absolute bottom-0 w-full">
        {props.date?.split("T")[0] + " às " + props.date?.split("T")[1]}
      </CardFooter>
    </Card>
  );
};

export default WCard;
