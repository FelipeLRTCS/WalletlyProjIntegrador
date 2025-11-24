import React from 'react'
import { Link } from 'react-router-dom'
import Curso from './Curso'
import { cursos } from '../data/data'
import Header from '../components/Header'

export default function CursosList() {
  return (
    <div>
      <Header/>
      {cursos.map((curso) => (
        <Link to={`/cursos/${curso.id}`} key={curso.id}>
          <Curso 
            Title={curso.Title} 
            textLeft={curso.textLeft} 
            textRight={curso.textRight} 
          />
        </Link>
      ))}
    </div>
  )
}
