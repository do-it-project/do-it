interface PropsType {
  message: string;
  className?: string;
}

const SpanError = ({ message, className }: PropsType) => {
  return (
    <span className={`text-error text-xs font-semibold mt-1.5 ${className}`}>
      {message}
    </span>
  );
};

export default SpanError;
