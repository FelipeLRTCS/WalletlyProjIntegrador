import React from "react";
import { useParams } from "react-router-dom";
import Header from "../components/Header";
import { cursos } from "../data/data";

export default function Curso() {
  const { id } = useParams();
  const curso = cursos.find((c) => c.id === parseInt(id));

  if (!curso) {
    return (
      <>
        <Header />
        <div className="container mx-auto px-4 py-12 text-center">
          <h1 className="text-4xl font-bold text-red-600">Curso não encontrado!</h1>
        </div>
      </>
    );
  }

  const { Title, textLeft, textRight } = curso;

  return (
    <>
      <Header />
      <div className="container mx-auto px-4 py-12 max-w-7xl">
        {/* Cabeçalho com linha decorativa */}
        <div className="flex items-center justify-center flex-col mb-16">
          <h1 className="text-4xl md:text-5xl font-bold text-gray-900 mb-4 text-center">
            {Title}
          </h1>
          <div className="w-24 h-1 bg-blue-600 rounded-full"></div>
        </div>

        {/* Conteúdo em duas colunas */}
        <div className="flex flex-col lg:flex-row gap-8 lg:gap-12 items-start">
          {/* Coluna esquerda */}
          <div className="w-full lg:w-1/2">
            <div className="bg-white rounded-xl shadow-lg p-8 hover:shadow-xl transition-shadow duration-300 border border-gray-100">
              <p className="text-gray-700 leading-relaxed text-lg whitespace-pre-line">
                {textLeft}
              </p>
            </div>
          </div>

          {/* Coluna direita */}
          <div className="w-full lg:w-1/2">
            <div className="bg-gradient-to-br from-blue-50 to-indigo-50 rounded-xl shadow-lg p-8 hover:shadow-xl transition-shadow duration-300 border border-blue-100">
              <div className="text-gray-700 leading-relaxed text-lg whitespace-pre-line">
                {textRight}
              </div>
            </div>
          </div>
        </div>
      </div>
    </>
  );
}
