import { RouterProvider } from "react-router-dom";
import routes from "./routes/routes"
import Perfil from "./pages/Perfil";

function App() {
  return <RouterProvider router={routes} />;
}

export default App;
