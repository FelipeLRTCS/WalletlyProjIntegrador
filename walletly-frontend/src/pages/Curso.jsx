import React from "react";

export default function Curso({ Title, textRight, textLeft }) {
  return (
    <>
      <div className="container ">
        <div className="flex items-center justify-center flex-col mb-20">
          <h1 className="text-2xl font-bold ">{Title}</h1>
        </div>
        <div className="flex flex-row gap-5">
          <div className="w-1/2">
            <p>{textLeft}</p>
          </div>
          <div className="w-1/2">{textRight}</div>
        </div>
      </div>
    </>
  );
}
