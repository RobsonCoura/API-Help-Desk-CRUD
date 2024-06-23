import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { _MatTableDataSource } from '@angular/material/table';
import { Chamado } from 'src/app/models/chamado';

@Component({
  selector: 'app-chamado-list',
  templateUrl: './chamado-list.component.html',
  styleUrls: ['./chamado-list.component.css']
})
export class ChamadoListComponent implements OnInit {

  // Dados de exemplo para a tabela
  ELEMENT_DATA: Chamado[] = [
    {
      id: 1,
      dataAbertura: '21/06/2021',
      dataFechamento: '21/06/2021',
      prioridade: 'ALTA',
      status: 'ANDAMENTO',
      titulo: 'Chamado 1',
      descricao: 'Teste do chamado 1',
      tecnico: 1,
      cliente: 6,
      nomeCliente: 'Robson Coura',
      nomeTecnico: 'Albert Einstein',
    }
  ]


  displayedColumns: string[] = ['id', 'titulo', 'cliente', 'tecnico', 'dataAbertura', 'prioridade', 'status', 'acoes']; // Colunas exibidas na tabela
  dataSource = new _MatTableDataSource<Chamado>(this.ELEMENT_DATA);  // Fonte de dados da tabela do tipo MatTableDataSource com os dados iniciais ELEMENT_DATA


  @ViewChild(MatPaginator) paginator: MatPaginator; // ViewChild para obter a instância do paginador MatPaginator da visualizaçãoF

  constructor() { }

  ngOnInit(): void {
  }

  // Método para aplicar filtro na tabela com base no evento de entrada do usuário
  applyFilter(event: Event) {
    const filterValue = (event.target as HTMLInputElement).value; // Obtém o valor do filtro digitado pelo usuário
    this.dataSource.filter = filterValue.trim().toLowerCase();     // Aplica o filtro à fonte de dados da tabela, convertendo o valor para minúsculas e removendo espaços em branco
  }

}
