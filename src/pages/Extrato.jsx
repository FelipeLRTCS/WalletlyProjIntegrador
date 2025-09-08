import React from "react";
import Logo from "../assets/logo.jpg";

function ExtratosPage() {
  return (
    <div className="flex items-center justify-center min-h-screen bg-gray-100">
      <div className="w-full max-w-sm bg-gray-500 rounded-lg shadow-lg overflow-hidden">
        {/* Header com o logo */}
        <img
          src={Logo}
          alt="Logo da Empresa"
          className="w-full h-28 object-cover"
        />

        {/* Conteúdo da página */}
        <div className="p-6 text-center">
          <h2 className="text-2xl font-semibold text-white mb-6">Extratos</h2>

          {/* Caixa de texto superior */}
          <div className="bg-cyan-900 text-white rounded-lg p-4 mb-6">
            <p className="text-sm">
              Para criarmos seu controle financeiro basta inserir todos os seus
              extratos bancários do período em que deseja.
            </p>
          </div>

          {/* Botão Adicionar */}
          <button className="w-3/4 bg-orange-500 text-white py-3 rounded-lg font-bold text-lg hover:bg-orange-600 transition duration-300">
            Adicionar
          </button>

          {/* Caixa de texto inferior */}
          <div className="bg-cyan-900 text-white rounded-lg p-4 mt-6">
            <p className="text-sm">
              O sistema carrega os anexos, distribuindo as transações entre
              despesas e receitas de acordo com os extratos, alimentando as
              telas do aplicativo.
            </p>
          </div>
        </div>
      </div>
    </div>
  );
}

export default ExtratosPage;
