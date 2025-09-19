import React from "react";
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip,
  Legend,
  ResponsiveContainer,
  ReferenceLine,
} from "recharts";

const IncomeExpenseChart = ({ dados }) => {
  return (
    <ResponsiveContainer width="100%" height="100%">
      <BarChart data={dados}>
        <CartesianGrid strokeDasharray="3 3" />
        <YAxis />
        <Tooltip formatter={(value) => `R$ ${value.toFixed(2)}`} />
        <Legend />
        <ReferenceLine y={0} stroke="#000" />
        <Bar dataKey="receitas" fill="#22C55E" />
        <Bar dataKey="despesas" fill="#EF4444" />
        <br />
      </BarChart>
    </ResponsiveContainer>
  );
};

export default IncomeExpenseChart;
