import './App.css';
import { BrowserRouter as Router } from "react-router-dom";
import Routes from "./components/Routes";
import { CalculatorContextProvider } from './context/CalculatorContext';

function App() {
  return (<Router>
    <CalculatorContextProvider>
      <div className="App">
        <header className="App-header">
          <Routes />
        </header>
      </div>
    </CalculatorContextProvider>
  </Router>
  );
}

export default App;
