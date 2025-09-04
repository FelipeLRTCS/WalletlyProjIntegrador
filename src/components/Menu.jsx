import React, { useState } from "react";

function HamburgerMenu() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="p-10 relative flex flex-col justify-items-start">
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
        <div className="absolute top-20 left-0 w-64 h-screen bg-gray-400 shadow-lg rounded-md overflow-hidden transition-all duration-300 ease-in-out flex flex-col justify-items-end">
          <ul className="flex flex-col justify-itens-start p-5 gap-3">
            <button className="text-center bg-cyan-900 rounded-lg text-white text-lg">
              Metas
            </button>
            <button className="text-center bg-cyan-900 rounded-lg text-white text-lg">
              Educação financeira
            </button>
            <button className="text-center bg-cyan-900 rounded-lg text-white text-lg">
              controle de orçamento
            </button>
            <button className="text-center bg-cyan-900 rounded-lg text-white text-lg">
              extrato
            </button>
          </ul>
        </div>
      )}
    </div>
  );
}

export default HamburgerMenu;
