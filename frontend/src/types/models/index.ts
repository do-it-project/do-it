export interface User {
  id: number;
  name: string;
  email: string;
  password?: string;
  phone: string;
  url_photo?: string;
  type: string;
  physicalAssessments?: PhysicalAssessment[];
  workouts?: Workout[];
}

export interface Token {
  token: string;
}

export interface Exercise {
  id: number;
  name: string;
  description: string;
  link_tutorial: string;
}

export interface WorkoutExercise {
  repetitions: number;
  rest_pause: number;
  sets: number;
  exercise: Exercise;
}

export interface Workout {
  id: number;
  name: string;
  comments: string;
  workout_exercises?: WorkoutExercise[];
}

export interface PhysicalAssessment {
  id: number;
  name: string;
  creationDate: string;
  comments: string;
  height: number;
  weight: number;
  fat_percentage: number;
  water_percentage: number;
  imc: number;
  waist_measurement: number;
  right_arm_measurement: number;
  left_arm_measurement: number;
  left_leg_measurement: number;
  right_leg_measurement: number;
  right_calf_measurement: number;
  left_calf_measurement: number;
  chest_measurement: number;
  abdominal_measurement: number;
}
