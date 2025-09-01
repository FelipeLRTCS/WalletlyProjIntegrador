import React, { useState } from "react";

function HamburgerMenu() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="p-10 relative flex flex-col justify-items-end">
      {/* O Botão de Hambúrguer */}
      <button onClick={toggleMenu} className="focus:outline-none relative z-10">
        <div className="w-6 h-6 flex flex-col justify-center items-center">
          <span
            className={`bg-black w-6 h-1 rounded-full transition-all duration-300 transform ${
              isOpen ? "rotate-45 translate-y-2" : ""
            }`}
          ></span>
          <span
            className={`bg-black w-6 h-1 my-1 rounded-full transition-all duration-300 ${
              isOpen ? "opacity-0" : ""
            }`}
          ></span>
          <span
            className={`bg-black w-6 h-1 rounded-full transition-all duration-300 transform ${
              isOpen ? "-rotate-45 -translate-y-2" : ""
            }`}
          ></span>
        </div>
      </button>

      {/* O Menu de Dados (renderização condicional) */}
      {isOpen && (
        <div className="absolute top-12 right-0 w-64 bg-white shadow-lg rounded-md overflow-hidden transition-all duration-300 ease-in-out flex flex-col justify-items-start">
          <ul className="py-2">
            <li className="px-4 py-2 hover:bg-gray-100 cursor-pointer border-b border-gray-200">
              Perfil
            </li>
            <li className="px-4 py-2 hover:bg-gray-100 cursor-pointer border-b border-gray-200">
              Configurações
            </li>
            <li className="px-4 py-2 hover:bg-gray-100 cursor-pointer">Sair</li>
          </ul>
        </div>
      )}
    </div>
  );
}

export default HamburgerMenu;
