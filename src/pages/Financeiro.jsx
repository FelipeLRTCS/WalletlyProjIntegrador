import React, { useState } from "react";
import ContentCard from "../components/ContentCard";
import FilterButton from "../components/FilterButton";
import Header from "../components/Header";

// 1. Array de Dados com os links e categorias (para o filtro)
const financialContent = [
  {
    id: 1,
    title: "Crédito", // Card original
    category: "Crédito",
    videoUrl: "https://youtu.be/q2p-pc0jyW0?si=wkuGIlATSijwNMUs", // Cartão de Crédito: como usar corretamente
    readUrl: "/materia/credito",
  },
  {
    id: 2,
    title: "Economia", // Card original
    category: "Invest", // Classificado como Invest para aparecer no filtro 'Invest'
    videoUrl: "https://youtu.be/dunfejH4Q9o?si=k00-v2w2EMbK-gJ8", // Aula Completa sobre EDUCAÇÃO FINANCEIRA
    readUrl: "/materia/economia",
  },
  {
    id: 3,
    title: "Pagamento", // Card original
    category: "Invest", // Classificado como Invest para aparecer no filtro 'Invest'
    videoUrl: "https://youtu.be/HSXcvFVtsdM?si=SRxKWPhKfJlgnACF", // Educação Financeira Básica: A REGRA DOS 3 FATORES
    readUrl: "/materia/pagamento",
  },
  {
    id: 4,
    title: "Estudos", // Card original
    category: "Invest", // Classificado como Invest para aparecer no filtro 'Invest'
    videoUrl: "https://youtu.be/bnMIu5e3H2Q?si=VufPbdZKe5bViS1m", // Como ECONOMIZAR dinheiro mudando sua MENTALIDADE
    readUrl: "/materia/estudos",
  },
];

const Financeiro = () => {
  const [selectedFilter, setSelectedFilter] = useState("Todos");

  // 2. Funções de ação atualizadas para abrir a URL em nova aba
  const handleWatch = (url, title) => {
    console.log(`Abrindo vídeo sobre ${title}: ${url}`);
    window.open(url, "_blank"); // Abre o link do YouTube em uma nova aba
  };

  const handleRead = (topic) => {
    console.log(`Abrir matéria sobre ${topic}`);
    // Implementar navegação/modal para a tela de leitura
  };

  // 3. Lógica para filtrar o conteúdo
  const filteredContent = financialContent.filter((item) => {
    if (selectedFilter === "Todos") {
      return true;
    }
    return item.category === selectedFilter;
  });

  return (
    // 'safeArea' -> div com altura mínima de tela e cor de fundo
    <div className="min-h-screen bg-gray-100">
      {/* 'header' -> tag <header> semântica com estilos do Tailwind */}
      <Header />

      {/* 'container' -> tag <main> com padding */}
      <main className="px-4 pt-3 max-w-4xl mx-auto">
        {/* 'sectionTitle' -> h2 */}
        <h2 className="text-xl font-semibold text-gray-800 my-4 text-center">
          Educação financeira
        </h2>

        {/* 'filterContainer' -> div com flex */}
        <div className="flex flex-row justify-center mb-5 space-x-2">
          <FilterButton
            title="Todos"
            isSelected={selectedFilter === "Todos"}
            onClick={() => setSelectedFilter("Todos")}
          />
          <FilterButton
            title="Crédito"
            isSelected={selectedFilter === "Crédito"}
            onClick={() => setSelectedFilter("Crédito")}
          />
          <FilterButton
            title="Invest"
            isSelected={selectedFilter === "Invest"}
            onClick={() => setSelectedFilter("Invest")}
          />
        </div>

        {/* 'cardsGrid' -> div com 'grid' (melhor que flex-wrap no web) */}
        <div className="grid grid-cols-1 sm:grid-cols-2 gap-4 pb-5">
          {/* 4. Mapeamento dos dados filtrados para renderizar os ContentCards */}
          {filteredContent.map((item) => (
            <ContentCard
              key={item.id}
              title={item.title}
              // Passa a URL do vídeo e o título para a função
              onClickWatch={() => handleWatch(item.videoUrl, item.title)}
              onClickRead={() => handleRead(item.title)}
            />
          ))}

          {filteredContent.length === 0 && (
            <p className="col-span-1 sm:col-span-2 text-center text-gray-500 mt-8">
              Nenhum conteúdo encontrado para a categoria **{selectedFilter}**.
            </p>
          )}
        </div>
      </main>
    </div>
  );
};

export default Financeiro;