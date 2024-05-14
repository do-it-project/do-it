import { useState } from "react";
import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";

type InputProps = {
  type: string;
  placeholder: string;
  IconLeft: React.ElementType;
  className?: string;
  register?: any;
};

const Input = ({
  type,
  placeholder,
  IconLeft,
  className,
  register,
}: InputProps) => {
  const [iconPassword, setIconPassword] = useState(false);
  const [inputType, setInputType] = useState(type);

  const togglePasswordVisibility = () => {
    setIconPassword((prev) => !prev);
    setInputType((prevType) => (prevType === "password" ? "text" : "password"));
  };

  return (
    <div className={`relative mt-1 ${className}`}>
      <IconLeft
        className="absolute bottom-[15px] left-3 h-5 w-5"
        color="#858585"
      />
      <input
        type={inputType}
        placeholder={placeholder}
        className="w-full rounded-lg p-3 pl-11 text-base flex items-center bg-gray-1 placeholder-gray-500 outline-none"
        {...register}
      />

      {type === "password" &&
        (iconPassword ? (
          <FaRegEye
            onClick={togglePasswordVisibility}
            className="absolute bottom-[15px] right-3 h-5 w-5 cursor-pointer"
            color="#858585"
          />
        ) : (
          <FaRegEyeSlash
            onClick={togglePasswordVisibility}
            className="absolute bottom-[15px] right-3 h-5 w-5 cursor-pointer"
            color="#858585"
          />
        ))}
    </div>
  );
};

export default Input;
