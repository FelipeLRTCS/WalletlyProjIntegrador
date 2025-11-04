import React, { useState } from "react";

function HamburgerMenu() {
  const [isOpen, setIsOpen] = useState(false);

  const toggleMenu = () => {
    setIsOpen(!isOpen);
  };

  return (
    <button onClick={toggleMenu} className="focus:outline-none">
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
  );
}

export default HamburgerMenu;
