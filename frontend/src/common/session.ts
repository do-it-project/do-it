const storeInSession = (key: string, value: string) => {
  return sessionStorage.setItem(key, value);
};

const getInSession = (key: string) => {
  return sessionStorage.getItem(key);
};

const removeFromSession = (key: string) => {
  return sessionStorage.removeItem(key);
};

export { storeInSession, getInSession, removeFromSession };
