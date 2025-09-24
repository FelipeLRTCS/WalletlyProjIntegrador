import React from "react";

const CustomTooltip = ({ active, payload, label }) => {
  // A propriedade 'active' indica se o mouse está sobre o gráfico
  if (active && payload && payload.length) {
    // O 'payload' é um array que contém os dados do item sobre o qual o mouse está
    const data = payload[0].payload;

    return (
      <div className="bg-white p-4 border border-gray-300 rounded shadow-lg text-sm">
        <p className="font-bold text-lg mb-1">{data.name}</p>
        <p>
          <span className="font-medium text-gray-600">Economizado:</span>
          <span className="ml-2 font-semibold text-blue-600">
            R$ {data.saved.toFixed(2)}
          </span>
        </p>
        <p>
          <span className="font-medium text-gray-600">Meta:</span>
          <span className="ml-2 font-semibold text-red-600">
            R$ {data.goal.toFixed(2)}
          </span>
        </p>
      </div>
    );
  }

  return null;
};

export default CustomTooltip;
