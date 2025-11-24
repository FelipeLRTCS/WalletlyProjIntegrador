import React from "react";

export default function CursoCard({ Title, courseDescription }) {
  return (
    <div className='flex align-center justify-center '>
      <div className="bg-white rounded-xl shadow-lg  hover:shadow-2xl transition-all duration-300 overflow-hidden border border-gray-100 hover:-translate-y-1 ">
        {/* Header do Card */}
        <div className="bg-gradient-to-r from-blue-500 to-purple-600 p-6">
          <h2 className="text-xl font-bold text-white">{Title}</h2>
        </div>

        {/* Conteúdo do Card */}
        <div className="p-6">
          <p className="text-gray-600 leading-relaxed">{courseDescription}</p>
        </div>

        {/* Footer do Card */}
        <div className="px-6 pb-6">
          <button className="w-full bg-blue-500 hover:bg-blue-600 text-white text-sm font-semibold py-3 rounded-lg transition-colors duration-200">
            Ver mais
          </button>
        </div>
      </div>
    </div>
  );
}
