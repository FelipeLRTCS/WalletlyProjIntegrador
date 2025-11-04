import React from "react";
import Menu from "./Menu";
import Icon from "../assets/icon.png";

const Header = () => {
  return (
    <header className="bg-blue-950 text-white p-4">
      <div className="container mx-auto flex justify-between items-center">
        <div className="flex items-center space-x-2">
          <div className="w-8 h-8 rounded-full bg-gray-600"></div>
          <span className="text-sm">Olá, nome</span>
        </div>

        <div className="hidden md:flex flex-row items-center space-x-2 ">
          <img src={Icon} alt="Logo" className="w-16 h-16" />
          <span className="text-xl font-bold">Walletly</span>
        </div>

        <div className="order-1 md:order-3">
          <Menu />
        </div>
      </div>
    </header>
  );
};

export default Header;
