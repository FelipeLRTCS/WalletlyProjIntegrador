import React, { useState } from "react";
// Atualize os imports para os novos nomes de arquivos
import DailyBalanceChart from "../components/DailyBalanceChart";
import DailyBalanceTable from "../components/DailyBalanceTable";
import IncomeExpenseChart from "../components/IncomeExpenseChart";
import MonthlyResultsTable from "../components/MonthlyResultsTable";

// Dados estáticos que simulam o extrato diário de um mês
const dadosDiariosSaldos = [
  { name: "1", saldo: 10530 },
  { name: "2", saldo: 12829 },
  { name: "3", saldo: 13570 },
  { name: "4", saldo: 20340 },
  { name: "5", saldo: 15324 },
  { name: "6", saldo: 10265 },
];

// Dados estáticos para a tabela diária de um mês
const dadosDiariosTabela = [
  { data: "1", saldo: 10530.0 },
  { data: "2", saldo: 12829.0 },
  { data: "3", saldo: 13570.0 },
  { data: "4", saldo: 20340.0 },
  { data: "5", saldo: 15324.0 },
  { data: "6", saldo: 10265.0 },
];

// Dados estáticos para o extrato anual completo
const dadosExtratoMensal = [
  { name: "JAN", receitas: 15256, despesas: 8000 },
  { name: "FEV", receitas: 18324, despesas: 10500 },
  { name: "MAR", receitas: 5648, despesas: 11000 },
  { name: "ABR", receitas: 12364, despesas: 9500 },
  { name: "MAI", receitas: 18326, despesas: 12000 },
  { name: "JUN", receitas: 11543, despesas: 8800 },
  { name: "JUL", receitas: 14500, despesas: 9000 },
  { name: "AGO", receitas: 16200, despesas: 11500 },
  { name: "SET", receitas: 19100, despesas: 13000 },
  { name: "OUT", receitas: 17800, despesas: 10500 },
  { name: "NOV", receitas: 20100, despesas: 14200 },
  { name: "DEZ", receitas: 22000, despesas: 15000 },
];

const dadosResultadoMensal = [
  { mes: "JAN", valor: 15256.0 },
  { mes: "FEV", valor: 18324.0 },
  { mes: "MAR", valor: 5648.0 },
  { mes: "ABR", valor: 12364.0 },
  { mes: "MAI", valor: 18326.0 },
  { mes: "JUN", valor: 11543.0 },
];

const Dashboard = () => {
  const [mesSelecionado, setMesSelecionado] = useState("AGO");

  const dadosFiltradosMensal = dadosExtratoMensal.filter(
    (item) => item.name === mesSelecionado
  );

  // Exemplo de como você faria para o gráfico diário também
  // const dadosDiariosDoMes = buscarDadosDiarios(mesSelecionado);

  return (
    <div className="container mx-auto p-8">
      <div className="bg-white p-6 rounded-lg shadow-md mb-8">
        <h2 className="text-xl font-bold mb-2 text-gray-800">
          Saldo Atual: R$ 10.265,00
        </h2>
        <div className="flex flex-col md:flex-row md:items-center">
          <div className="w-full md:w-2/3 h-80">
            {/* Título dinâmico */}
            <h3 className="text-lg font-semibold mb-4 text-gray-700">
              Saldo de {mesSelecionado}
            </h3>
            {/* Use o novo nome do componente */}
            <DailyBalanceChart dados={dadosDiariosSaldos} />
          </div>
          <div className="w-full md:w-1/3 mt-6 md:mt-0 md:ml-6">
            <h3 className="text-lg font-semibold mb-4 text-gray-700">
              Saldos em {mesSelecionado}
            </h3>
            {/* Use o novo nome do componente */}
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
          <div className="w-full md:w-1/3 mt-6 md:mt-0 md:ml-6">
            <h3 className="text-lg font-semibold mb-4 text-gray-700">
              Demonstração de Resultado
            </h3>
            <MonthlyResultsTable dados={dadosResultadoMensal} />
          </div>
        </div>
      </div>
    </div>
  );
};

export default Dashboard;
