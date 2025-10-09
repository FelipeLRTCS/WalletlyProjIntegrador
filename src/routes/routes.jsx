import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";

import Login from "../pages/Login";
import Cadastro from "../pages/Cadastro";
import Start from "../pages/Start";
import Extrato from "../pages/Extrato";
import Dashboard from "../pages/Dashboard";
import Metas from "../pages/GoalChart";
import EsqueceuSenha from "../pages/EsqueceuSenha";

// 2. Crie e exporte o roteador
const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route index element={<Login />} />

      <Route path="cadastro" element={<Cadastro />} />

      <Route path="start" element={<Start />} />

      <Route path="extrato" element={<Extrato />} />

      <Route path="dashboard" element={<Dashboard />} />

      <Route path="metas" element={<Metas />} />

      <Route path="EsqueceuSenha" element={<EsqueceuSenha />} />
    </Route>
  )
);

export default router;
