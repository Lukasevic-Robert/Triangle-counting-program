import React, { createContext, useState } from 'react';

const CalculatorContext = createContext();
const { Provider } = CalculatorContext;

const CalculatorContextProvider = ({ children }) => {

    const [response, setResponse] = useState(0);

    return (
        <Provider value={{
            response, setResponse
        }}>
            {children}
        </Provider>
    )
};
export { CalculatorContext, CalculatorContextProvider };
