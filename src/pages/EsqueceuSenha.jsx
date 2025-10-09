import React, { useState } from "react";
import { Link } from "react-router-dom"; // Importe o Link para navegação
import Logo from "../assets/logo.jpg";
import { Input } from "antd";

const ForgotPassword = () => {
  const [email, setEmail] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    // Lógica para enviar o email de recuperação
    console.log("Solicitação de recuperação para o e-mail:", email);
    alert(
      "Se o e-mail estiver cadastrado, você receberá um link para redefinir sua senha."
    );
    setEmail(""); // Limpa o campo após o envio
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
          <h2 className="text-xl font-bold text-white text-center">
            Recuperar Senha
          </h2>
          <p className="text-sm text-gray-200 text-center">
            Insira seu e-mail para receber as instruções de como redefinir sua
            senha.
          </p>

          <Input
            id="email"
            type="email"
            placeholder="E-mail"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            required
            className="mt-1 h-12 w-full px-4 text-lg rounded-md border border-gray-300 focus:outline-none focus:ring-2 focus:ring-blue-500"
          />

          <button
            type="submit"
            className="h-12 w-full bg-white text-gray-500 font-bold text-lg rounded-md transition-transform transform hover:scale-105"
          >
            Enviar Link
          </button>

          <div className="flex justify-center w-full text-sm text-white">
            <Link to="/" className="hover:underline">
              Voltar para o Login
            </Link>
          </div>
        </div>
      </form>
    </div>
  );
};

export default ForgotPassword;
