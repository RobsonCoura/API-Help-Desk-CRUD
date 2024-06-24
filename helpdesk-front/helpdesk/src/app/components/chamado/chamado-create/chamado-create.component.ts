import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-chamado-create',
  templateUrl: './chamado-create.component.html',
  styleUrls: ['./chamado-create.component.css']
})
export class ChamadoCreateComponent implements OnInit {

  // Definição dos controles de formulário com validações
  prioridade: FormControl = new FormControl(null, [Validators.required])
  status: FormControl = new FormControl(null, [Validators.required])
  titulo: FormControl = new FormControl(null, [Validators.required])
  descricao: FormControl = new FormControl(null, [Validators.required])
  tecnico: FormControl = new FormControl(null, [Validators.required])
  cliente: FormControl = new FormControl(null, [Validators.required])

  // Construtor do componente
  constructor() { }

  // Método do ciclo de vida do Angular que é chamado após a inicialização do componente
  ngOnInit(): void {
  }

  // Método para validar se todos os campos do formulário são válidos
  validaCampos(): boolean {
    return this.prioridade.valid && this.status.valid &&
      this.titulo.valid && this.descricao.valid &&
      this.tecnico.valid && this.cliente.valid
  }
}

