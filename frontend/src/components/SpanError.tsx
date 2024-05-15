interface PropsType {
  message: string;
  className?: string;
}

const SpanError = ({ message, className }: PropsType) => {
  return (
    <span className={`text-error text-xs font-normal ${className}`}>
      {message}
    </span>
  );
};

export default SpanError;
