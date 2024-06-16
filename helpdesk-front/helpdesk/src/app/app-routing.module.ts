import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { NavComponent } from './components/nav/nav.component';
import { HomeComponent } from './components/home/home.component';

const routes: Routes = [
  {
    path: '', // Define o caminho da rota como vazio, indicando a rota raiz.
    // Associa o componente NavComponent a este caminho da rota.
    component: NavComponent, children: [
      { path: 'home', component: HomeComponent } // Rota filha do componente NAVE
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
