import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

// Decorador Component indicando que é um componente Angular
@Component({
  selector: 'app-tecnico-create', 
  templateUrl: './tecnico-create.component.html', 
  styleUrls: ['./tecnico-create.component.css'] 
})
export class TecnicoCreateComponent implements OnInit {

  // Controles de formulário para nome, cpf, email e senha
  nome: FormControl = new FormControl(null, Validators.minLength(3));
  cpf: FormControl = new FormControl(null, Validators.required);
  email: FormControl = new FormControl(null, Validators.email);
  senha: FormControl = new FormControl(null, Validators.minLength(3));

  constructor() { }

  ngOnInit(): void {
    // Hook de ciclo de vida, lógica de inicialização pode ser colocada aqui
  }

  // Método para verificar se todos os campos do formulário são válidos
  validaCampos(): boolean {
    return this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid;
  }

}

