// src/rotas/index.js

import {
  Route,
  createBrowserRouter,
  createRoutesFromElements,
} from "react-router-dom";

import Login from "../pages/Login";
import Cadastro from "../pages/Cadastro";
import Start from "../pages/Start";

// 2. Crie e exporte o roteador
const router = createBrowserRouter(
  createRoutesFromElements(
    <Route path="/">
      <Route index element={<Login />} />

      <Route path="cadastro" element={<Cadastro />} />

      <Route path="start" element={<Start />} />
    </Route>
  )
);

export default router;
