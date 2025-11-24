// src/components/FilterButton.js

import React from "react";

// Trocamos 'onPress' por 'onClick' para o padrão web
const FilterButton = ({ title, isSelected, onClick }) => {
  return (
    <button
      onClick={onClick}
      className={`
        py-2 px-4 mx-1 rounded-full 
        text-white text-sm font-semibold 
        transition-colors duration-200
        ${
          isSelected
            ? "bg-[#37474F]" // styles.selectedButton
            : "bg-[#90A4AE] hover:bg-[#7a8c94]" // styles.unselectedButton + efeito hover
        }
      `}
    >
      {title}
    </button>
  );
};

export default FilterButton;
