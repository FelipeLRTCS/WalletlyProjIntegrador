import React, { useState } from "react";
import { Link } from "react-router-dom"; // 1. Importe o componente Link

function Menu() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <div className="">
      <button
        onClick={toggleMenu}
        className="focus:outline-none relative z-30 p-4"
      >
        <div className="w-6 h-6 flex flex-col justify-center items-center">
          {/* Animação */}
          <span
            className={`bg-white w-6 h-1 rounded-full transition-all duration-300 transform ${
              isOpen ? "rotate-45 translate-y-2" : ""
            }`}
          ></span>
          <span
            className={`bg-white w-6 h-1 my-1 rounded-full transition-all duration-300 ${
              isOpen ? "opacity-0" : ""
            }`}
          ></span>
          <span
            className={`bg-white w-6 h-1 rounded-full transition-all duration-300 transform ${
              isOpen ? "-rotate-45 -translate-y-2" : ""
            }`}
          ></span>
        </div>
      </button>

      {isOpen && (
        <div className="absolute top-0 sm:right-0 w-64 h-screen bg-blue-950 shadow-lg z-20">
          <ul className="flex flex-col pt-24 p-5 gap-3">
            <Link
              to="/Metas"
              className="w-full text-center p-2 bg-cyan-900 rounded-lg text-white text-lg border border-white"
            >
              Metas
            </Link>
            <Link
              to="/"
              className="w-full text-center p-2 bg-cyan-900 rounded-lg text-white text-lg border border-white"
            >
              Educação financeira
            </Link>
            <Link
              to="/dashboard"
              className="w-full text-center p-2 bg-cyan-900 rounded-lg text-white text-lg border border-white"
            >
              Controle de orçamento
            </Link>
            <Link
              to="/extrato"
              className="w-full text-center p-2 bg-cyan-900 rounded-lg text-white text-lg border border-white"
            >
              Extrato
            </Link>
            <Link
              to="/cursos"
              className="w-full text-center p-2 bg-cyan-900 rounded-lg text-white text-lg border border-white"
            >
              Cursos
            </Link>
          </ul>
        </div>
      )}
    </div>
  );
}

export default Menu;
