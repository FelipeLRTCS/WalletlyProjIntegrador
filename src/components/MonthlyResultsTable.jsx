import React from "react";

const MonthlyResultsTable = ({ dados }) => {
  return (
    <div className="w-full bg-gray-100 rounded-lg overflow-hidden">
      <table className="min-w-full text-center divide-y divide-gray-300">
        <thead className="bg-gray-200">
          <tr>
            <th className="py-2 px-4 text-sm font-semibold text-gray-600">
              Demonstração Resultado
            </th>
          </tr>
        </thead>
        <tbody className="bg-white divide-y divide-gray-200">
          {dados.map((item, index) => (
            <tr key={index}>
              <td className="py-2 text-sm text-gray-800">
                {item.mes} - R$ {item.valor.toFixed(2)}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default MonthlyResultsTable;
