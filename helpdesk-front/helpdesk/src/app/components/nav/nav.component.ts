// Importações necessárias do Angular
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router'; // Importa Router para navegação

// Componente Angular para a barra de navegação
@Component({
  selector: 'app-nav', // Seletor do componente
  templateUrl: './nav.component.html', // Template HTML associado ao componente
  styleUrls: ['./nav.component.css'] // Estilos CSS associados ao componente
})
export class NavComponent implements OnInit {

  // Construtor do componente, injeta o serviço Router para navegação
  constructor(private router: Router) { }

  // Método executado quando o componente é inicializado
  ngOnInit(): void {
    this.router.navigate(['home']); // Navega para a rota 'home' ao inicializar
  }

}
