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
import { Dialog, DialogContent, DialogHeader, DialogTitle } from "../ui/dialog";
import { WorkoutExercise } from "@/types/models";
import {
  Accordion,
  AccordionContent,
  AccordionItem,
  AccordionTrigger,
} from "../ui/accordion";

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
  function convertToEmbedUrl(url: string) {
    const videoId = url.split("youtu.be/")[1].split("?")[0];
    const params = url.split("?")[1];
    return `https://youtube.com/embed/${videoId}?${params}`;
  }

  return (
    <Card
      className={`${props.classname} w-[350px] border-none drop-shadow-xl   shadow-lg shadow-black-4`}
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

                  <DialogContent className="max-w-[400px] max-h-[400px] lg:max-h-[600px] sm:max-w-[600px] overflow-scroll md:overflow-auto custom-scrollbar">
                    <DialogHeader>
                      <DialogTitle className="text-blue-1">
                        {props.name}
                      </DialogTitle>
                    </DialogHeader>

                    <div className="my-3 text-gray-1">
                      {props.workout_exercises?.map((we) => (
                        <Accordion type="single" collapsible>
                          <AccordionItem value={we.id.toString()}>
                            <div className="flex justify-between items-center bg-black-4/70 py-2 px-3 rounded-md my-2">
                              <h1 className="capitalize">{we.exercise.name}</h1>
                              <div className="flex gap-3 items-center">
                                <span>
                                  {we.sets}x{we.repetitions} - {we.rest_pause}s
                                </span>

                                <AccordionTrigger>
                                  <LuEye className="text-blue-1 cursor-pointer transition-colors hover:text-blue-2 duration-400 hover:scale-[1.1] active:scale-[1.0] h-15 w-15 text-xl" />
                                </AccordionTrigger>
                              </div>
                            </div>

                            <AccordionContent>
                              <div className="flex text-gray-1 flex-col gap-5  px-2 mt-2 mb-3">
                                <p className="text-blue-2 font-semibold">
                                  Descrição:{" "}
                                  <span className="text-gray-3">
                                    {we.exercise.description}{" "}
                                  </span>
                                </p>
                                <iframe
                                  className="sm:w-[515px] sm:h-[300px] w-full min-h-[300px]"
                                  src={convertToEmbedUrl(
                                    we.exercise.link_tutorial
                                  )}
                                  frameBorder={0}
                                  title="YouTube Tutorial"
                                  allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share"
                                  referrerPolicy="strict-origin-when-cross-origin"
                                  allowFullScreen
                                ></iframe>
                              </div>
                            </AccordionContent>
                          </AccordionItem>
                        </Accordion>
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
