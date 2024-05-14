import AnimationWrapper from "./AnimationWrapper";

type PropsType = {
  children: React.ReactElement;
};

const LayoutSign = ({ children }: PropsType) => {
  return (
    <div className="bg-black-1 flex justify-center items-center w-full h-screen overflow-hidden">
      <AnimationWrapper>{children}</AnimationWrapper>
    </div>
  );
};

export default LayoutSign;
