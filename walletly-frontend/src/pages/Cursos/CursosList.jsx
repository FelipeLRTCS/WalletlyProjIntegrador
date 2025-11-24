import React from 'react'
import { Link } from 'react-router-dom'
import CursoCard from './CursoCard'
import { cursos } from '../../data/data'
import Header from '../../components/Header'

export default function CursosList() {
  return (
    <div className="min-h-screen bg-gray-50">
      <Header/>
      
      <div className="container mx-auto px-4 py-8 max-w-7xl">
        <div className="grid grid-cols-1 md:grid-cols-2 gap-6 lg:gap-20">
          {cursos.map((curso) => (
            <Link 
              to={`/cursos/${curso.id}`} 
              key={curso.id}
              className="transform transition-transform duration-300 hover:scale-105"
            >
              <CursoCard 
                Title={curso.Title} 
                courseDescription={curso.courseDescription}
              />
            </Link>
          ))}
        </div>
      </div>
    </div>
  )
}