// Importações de módulos e classes do Angular e do Material
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { Tecnico } from 'src/app/models/tecnico'; // Importa o modelo Tecnico

// Decorador @Component para o componente TecnicoListComponent
@Component({
  selector: 'app-tecnico-list',
  templateUrl: './tecnico-list.component.html', // Arquivo HTML de template
  styleUrls: ['./tecnico-list.component.css'] // Arquivo de estilos CSS
})
export class TecnicoListComponent implements OnInit {

  // Dados de exemplo para a tabela
  ELEMENT_DATA: Tecnico[] = [
    {
      id: 1,
      nome: 'Robson Cezar',
      cpf: '123.456.789-10',
      email: 'robson@email.com',
      senha: '1234',
      perfis: ['0'],
      dataCriacao: '15/08/2022'
    }
  ];

  // Colunas exibidas na tabela
  displayedColumns: string[] = ['id', 'nome', 'cpf', 'email', 'acoes'];

  // Fonte de dados da tabela do tipo MatTableDataSource com os dados iniciais ELEMENT_DATA
  dataSource = new MatTableDataSource<Tecnico>(this.ELEMENT_DATA);

  constructor() { }

  ngOnInit(): void {
  }

  // ViewChild para obter a instância do paginador MatPaginator da visualização
  @ViewChild(MatPaginator) paginator: MatPaginator;

  // Método ngAfterViewInit para configurar o paginador após a visualização do componente
  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
  }

}
