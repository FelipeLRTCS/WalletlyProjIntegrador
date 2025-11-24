import React from "react";
import { useParams } from "react-router-dom";
import { cursos } from "../consts/consts";
import Header from "../components/Header";

export default function CursoPage() {
  const { id } = useParams();
  const curso = cursos.find((c) => c.id === parseInt(id));

  if (!curso) {
    return <div>Curso não encontrado</div>;
  }

  return (
    <>
      <Header />
      <div className="container ">
        <div className="flex items-center justify-center flex-col mb-20">
          <h1 className="text-2xl font-bold ">{curso.Title}</h1>
        </div>
        <div className="flex flex-row gap-5">
          <div className="w-1/2">
            <p>{curso.textLeft}</p>
          </div>
          <div className="w-1/2">{curso.textRight}</div>
        </div>
      </div>
    </>
  );
}
