// src/components/Header.js

import React from 'react';

const Header = () => {
  return (
    <header className="bg-gray-800 text-white p-4">
      <div className="container mx-auto flex justify-between items-center">
        {/* Menu Hamburguer */}
        <div className="flex items-center">
          <div className="w-8 h-8 flex flex-col justify-around cursor-pointer">
            <span className="block w-full h-1 bg-white"></span>
            <span className="block w-full h-1 bg-white"></span>
            <span className="block w-full h-1 bg-white"></span>
          </div>
        </div>

        {/* Logo Walletly */}
        <div className="flex items-center space-x-2">
          {/* Ícone (simulado) */}
          <div className="w-6 h-6 rounded-full bg-blue-500"></div>
          <span className="text-xl font-bold">Walletly</span>
        </div>

        {/* Saudação e Avatar */}
        <div className="flex items-center space-x-2">
          <span className="text-sm">Olá nome</span>
          {/* Avatar (simulado) */}
          <div className="w-8 h-8 rounded-full bg-gray-600"></div>
        </div>
      </div>
    </header>
  );
};

export default Header;