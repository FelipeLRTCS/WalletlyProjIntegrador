//import Start from './components/Start'
import { RouterProvider } from "react-router-dom";
import "./App.css";
import Cadastro from "./pages/Cadastro";
import routes from "./routes/routes";

function App() {
  return <RouterProvider router={routes} />;
}

export default App;
