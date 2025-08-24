import { useState } from "react";
import Logo from "../assets/logo.jpg";
import { Input } from "antd";

function Cadastro() {
  const [nome, setNome] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [showPassword, setShowPassword] = useState(false);
  const [message, setMessage] = useState("");

  const handleSubmit = (event) => {
    event.preventDefault();

    // Validação: checa se as senhas são iguais
    if (password !== confirmPassword) {
      setMessage("As senhas não coincidem!");
      return;
    }

    // Se tudo estiver certo, exibe no console
    console.log({ nome, email, password });
    setMessage("Cadastro realizado com sucesso!");
    // Aqui você chamaria a sua API para criar o usuário
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
          {/* Campo Nome */}
          <Input
            type="text"
            placeholder="Nome Completo"
            value={nome}
            onChange={(e) => setNome(e.target.value)}
            className="h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />

          {/* Campo E-mail */}
          <Input
            id="email"
            type="email"
            placeholder="E-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="mt-1 h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />

          {/* Campo Senha */}
          <div className="relative w-full">
            <Input.Password
              type={showPassword ? "text" : "password"}
              placeholder="Senha"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              className="h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
              required
            />
          </div>

          {/* Campo Confirmar Senha */}
          <Input.Password
            type="password"
            placeholder="Confirmar Senha"
            value={confirmPassword}
            onChange={(e) => setConfirmPassword(e.target.value)}
            className="h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-400"
            required
          />

          {/* Exibição de mensagens */}
          {message && (
            <p
              className={`text-center text-sm ${
                message.includes("sucesso") ? "text-green-300" : "text-red-300"
              }`}
            >
              {message}
            </p>
          )}

          {/* Botão de Cadastro */}
          <button
            type="submit"
            className="h-12 w-full bg-white text-gray-500 font-bold text-lg rounded-md transition-transform transform hover:scale-105"
          >
            Cadastrar
          </button>

          {/* Link para a tela de Login */}
          <div className="text-center text-sm text-white">
            <a href="/" className="hover:underline">
              Já tem uma conta? Faça login
            </a>
          </div>
        </div>
      </form>
    </div>
  );
}

export default Cadastro;
