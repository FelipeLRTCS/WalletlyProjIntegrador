// src/components/ContentCard.js

import React from "react";

// Trocamos 'onPress...' por 'onClick...' para o padrão web
const ContentCard = ({ title, onClickWatch, onClickRead }) => {
  return (
    // cardContainer
    <div
      className="
      bg-[#37474F] p-4 rounded-lg w-full min-h-[150px] 
      flex flex-col items-center justify-between shadow-md
    "
    >
      {/* cardTitle */}
      <h3 className="text-lg font-bold text-white mb-3 text-center">{title}</h3>

      {/* Container para os botões */}
      <div className="w-full flex flex-col items-center">
        {/* button + buttonText */}
        <button
          onClick={onClickWatch}
          className="
          bg-[#1E88E5] py-2 px-5 rounded-md mt-2 w-[90%] 
          text-white text-[15px] font-semibold 
          transition-colors hover:bg-[#1572C3]
        "
        >
          Assistir
        </button>

        {/* button + buttonText */}
        <button
          onClick={onClickRead}
          className="
          bg-[#1E88E5] py-2 px-5 rounded-md mt-2 w-[90%] 
          text-white text-[15px] font-semibold 
          transition-colors hover:bg-[#1572C3]
        "
        >
          Ler
        </button>
      </div>
    </div>
  );
};

export default ContentCard;
