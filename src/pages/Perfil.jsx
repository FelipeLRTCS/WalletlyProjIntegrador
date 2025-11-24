import React, { useState, useEffect } from 'react';
import Logo from "../assets/logo.jpg";

function Perfil() {
  const [user, setUser] = useState(null);
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    setTimeout(() => {
      setUser({
        name: "Fulano de Tal",
        email: "fulano@exemplo.com",
        bio: "Desenvolvedor React em constante aprendizado.",
        photoUrl: "https://via.placeholder.com/150",
      });
      setIsLoading(false);
    }, 1000);
  }, []);

  if (isLoading) {
    return (
      <div className="flex justify-center items-center h-screen bg-gray-100">
        <p className="text-xl text-gray-700">Carregando perfil...</p>
      </div>
    );
  }

  if (!user) {
    return (
      <div className="flex justify-center items-center h-screen bg-gray-100">
        <p className="text-xl text-red-600">Perfil não encontrado.</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 flex items-center justify-center p-4">
      <div className="bg-white shadow-xl rounded-lg p-8 w-full max-w-lg">
        <div className="flex flex-col items-center border-b pb-6 mb-6">
          <img
            src={Logo}
            alt="Logo da Empresa"
            className="w-full h-28 object-cover"
          />
          <img
            src={user.photoUrl}
            alt="Foto de Perfil"
            className="w-32 h-32 rounded-full object-cover border-4 border-indigo-500 flex justify-center mt-4"
          />
          <h1 className="text-3xl font-bold mt-4 text-gray-800">{user.name}</h1>
          <p className="text-sm text-indigo-600">Membro desde 2023</p>
        </div>

        <div className="space-y-4">
          <div className="flex justify-between items-center p-3 bg-gray-50 rounded-md">
            <span className="font-semibold text-gray-700">Email:</span>
            <span className="text-gray-600">{user.email}</span>
          </div>

          <div className="p-3">
            <h2 className="text-xl font-semibold mb-2 text-gray-800">
              Biografia
            </h2>
            <p className="text-gray-600 leading-relaxed italic">{user.bio}</p>
          </div>
          <div className="p-3">
            <h2 className="text-xl font-semibold mb-2 text-gray-800">Valor de Renda</h2>
            <p className="text-gray-600 leading-relaxed italic">{user.renda}</p>
          </div>
        </div>

        <div className="mt-8">
          <button
            className="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-bold py-2 px-4 rounded-lg transition duration-300"
            onClick={() => alert("Função de edição chamada!")}
          >
            Editar Perfil
          </button>
        </div>
      </div>
    </div>
  );
}

export default Perfil;
