import { useState } from "react";
import { useNavigate, Link } from "react-router-dom"; // 1. Importe useNavigate e Link
import Logo from "../assets/logo.jpg";
import { Input } from "antd";

function Login() {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const navigate = useNavigate(); // 2. Inicialize o hook

  const handleSubmit = (event) => {
    event.preventDefault();

    // 3. Lógica de navegação
    // Como você pediu, ele vai navegar se qualquer email e senha forem digitados.
    // Os campos 'required' no input já garantem que eles não estarão vazios.
    if (email && password) {
      console.log("Login realizado, redirecionando...");
      navigate("/dashboard"); // Navega para a rota /dashboard
    }
  };

  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <form
        onSubmit={handleSubmit}
        className="flex flex-col bg-gray-500 w-full max-w-sm rounded-xl shadow-lg overflow-hidden"
      >
        <img
          src={Logo}
          alt="Logo da Empresa"
          className="w-full h-28 object-cover"
        />

        <div className="flex flex-col p-8 gap-y-6">
          <Input
            id="email"
            type="email"
            placeholder="E-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="mt-1 h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />

          <Input.Password
            placeholder="Senha"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />

          <button
            type="submit"
            className="h-12 w-full bg-white text-gray-500 font-bold text-lg rounded-md transition-transform transform hover:scale-105"
          >
            Login
          </button>

          {/* 4. Troquei <a> por <Link> para uma navegação mais fluida */}
          <div className="flex justify-between w-full text-sm text-white">
            <Link to="/cadastro" className="hover:underline">
              Criar conta
            </Link>
            <Link to="/esqueci-a-senha" className="hover:underline">
              Esqueceu a senha?
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Login;
