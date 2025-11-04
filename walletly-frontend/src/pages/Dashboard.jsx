import React, { useState } from "react";
import DailyBalanceChart from "../components/DailyBalanceChart";
import DailyBalanceTable from "../components/DailyBalanceTable";
import IncomeExpenseChart from "../components/IncomeExpenseChart";
import MonthlyResultsTable from "../components/MonthlyResultsTable";
import Header from "../components/Header";
import {
  dadosDiariosSaldos,
  dadosDiariosTabela,
  dadosExtratoMensal,
  dadosResultadoMensal,
} from "../data/data";

const Dashboard = () => {
  const [mesSelecionado, setMesSelecionado] = useState("AGO");

  const dadosFiltradosMensal = dadosExtratoMensal.filter(
    (item) => item.name === mesSelecionado
  );

  return (
    // 1. Div principal para ocupar a tela inteira e dar uma cor de fundo
    <div className="min-h-screen bg-gray-100">
      {/* O Header agora está aqui fora, ocupando a largura total */}
      <Header />

      {/* 2. Um novo container APENAS para o conteúdo do dashboard */}
      <main className="container mx-auto p-8">
        <div className="bg-white p-6 rounded-lg shadow-md mb-8">
          <h2 className="text-xl font-bold mb-2 text-gray-800">
            Saldo Atual: R$ 10.265,00
          </h2>
          <div className="flex flex-col md:flex-row md:items-center">
            <div className="w-full md:w-2/3 h-80">
              <h3 className="text-lg font-semibold mb-4 text-gray-700">
                Saldo de {mesSelecionado}
              </h3>
              <DailyBalanceChart dados={dadosDiariosSaldos} />
            </div>
            <div className="w-full md:w-1/3 mt-6 md:mt-0 md:ml-6">
              <h3 className="text-lg font-semibold mb-4 text-gray-700">
                Saldos em {mesSelecionado}
              </h3>
              <DailyBalanceTable dados={dadosDiariosTabela} />
            </div>
          </div>
        </div>

        <div className="bg-white p-6 rounded-lg shadow-md">
          <div className="flex flex-col md:flex-row md:items-center">
            <div className="w-full md:w-2/3 h-80">
              <h3 className="text-lg font-semibold mb-4 text-gray-700">
                Receitas e Despesas
                <select
                  className="ml-4 p-1 rounded-md border border-gray-300"
                  value={mesSelecionado}
                  onChange={(e) => setMesSelecionado(e.target.value)}
                >
                  {dadosExtratoMensal.map((item) => (
                    <option key={item.name} value={item.name}>
                      {item.name}
                    </option>
                  ))}
                </select>
              </h3>
              <IncomeExpenseChart dados={dadosFiltradosMensal} />
            </div>
            <br />
            <div className="w-full md:w-1/3 mt-6 md:mt-0 md:ml-6">
              <h3 className="text-lg font-semibold mb-4 text-gray-700">
                Demonstração de Resultado
              </h3>
              <MonthlyResultsTable dados={dadosResultadoMensal} />
            </div>
          </div>
        </div>
      </main>
    </div>
  );
};

export default Dashboard;
