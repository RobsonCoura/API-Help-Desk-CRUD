import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';
import { HomeComponent } from './components/home/home.component';
import { TecnicoListComponent } from './components/tecnico/tecnico-list/tecnico-list.component';
import { LoginComponent } from './components/login/login.component';
import { AuthGuard } from './auth/auth.guard'; // Importa o guarda de autenticação
import { TecnicoCreateComponent } from './components/tecnico/tecnico-create/tecnico-create.component';

// Definição das rotas da aplicação
const routes: Routes = [
  { path: 'login', component: LoginComponent }, // Rota para o componente de login

  // Rota principal, associada ao componente NavComponent e protegida pelo AuthGuard
  {
    path: '', // Define o caminho da rota como vazio, indicando a rota raiz.
    component: NavComponent, // Associa o componente NavComponent a este caminho da rota.
    canActivate: [AuthGuard], // Protege a rota usando o AuthGuard
    children: [
      { path: 'home', component: HomeComponent }, // Rota filha para o componente Home
      { path: 'tecnicos', component: TecnicoListComponent }, // Rota filha para o componente TecnicoListComponent
      { path: 'tecnicos/create', component: TecnicoCreateComponent } // Rota filha para o componente TecnicoListComponent
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)], // Configuração do módulo de roteamento com as rotas definidas
  exports: [RouterModule] // Exporta o módulo de roteamento para ser utilizado na aplicação
})
export class AppRoutingModule { }
