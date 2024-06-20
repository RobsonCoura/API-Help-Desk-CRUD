import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { Tecnico } from 'src/app/models/tecnico';
import { TecnicoService } from 'src/app/services/tecnico.service';

// Decorador Component indicando que é um componente Angular
@Component({
  selector: 'app-tecnico-create', // Seletor usado nos templates HTML
  templateUrl: './tecnico-create.component.html', // Arquivo de template para o componente
  styleUrls: ['./tecnico-create.component.css'] // Folhas de estilo para o componente
})
export class TecnicoCreateComponent implements OnInit {

  // Objeto que representa os dados do técnico a ser criado
  tecnico: Tecnico = {
    id: '',
    nome: '',
    cpf: '',
    email: '',
    senha: '',
    perfis: [],
    dataCriacao: ''
  }

  // Controles de formulário para nome, cpf, email e senha
  nome: FormControl = new FormControl(null, Validators.minLength(3));
  cpf: FormControl = new FormControl(null, Validators.required);
  email: FormControl = new FormControl(null, Validators.email);
  senha: FormControl = new FormControl(null, Validators.minLength(3));

  constructor(
    private service: TecnicoService, // Serviço para interação com dados de técnicos
    private toast: ToastrService, // Serviço para notificações/toasts
    private router: Router, // Serviço para navegação
  ) { }

  ngOnInit(): void {
    // Hook de ciclo de vida, lógica de inicialização pode ser colocada aqui
  }

  // Método para criar um novo técnico
  create(): void {
    this.service.create(this.tecnico).subscribe(
      () => {
        this.toast.success('Técnico cadastrado com sucesso', 'Cadastro'); // Toast de sucesso
        this.router.navigate(['tecnicos']); // Redireciona para lista de técnicos
      },
      (error) => {
        console.error('Erro ao criar técnico:', error); // Log do erro para depuração
        const errorMessage = this.getErrorMessage(error); // Obtém mensagem de erro
        if (errorMessage) {
          this.toast.error(errorMessage); // Exibe toast de erro
        }
      }
    );
  }

  // Método privado para obter mensagem de erro detalhada
  private getErrorMessage(error: any): string {
    if (!error) return 'Ocorreu um erro ao processar a requisição.';
    if (error.error && Array.isArray(error.error.errors)) {
      error.error.errors.forEach((element: any) => {
        if (element.message) this.toast.error(element.message);
      });
      return '';
    }
    return error.error?.massage || error.error?.message || typeof error.error === 'string'
      ? error.error.massage || error.error.message || error.error
      : error.message || 'Ocorreu um erro ao processar a requisição.';
  }

  // Adiciona ou remove um perfil do técnico
  addPerfil(perfil: any): void {
    if (this.tecnico.perfis.includes(perfil)) {
      this.tecnico.perfis.splice(this.tecnico.perfis.indexOf(perfil), 1);
    } else {
      this.tecnico.perfis.push(perfil);
    }
  }

  // Método para verificar se todos os campos do formulário são válidos
  validaCampos(): boolean {
    return this.nome.valid && this.cpf.valid && this.email.valid && this.senha.valid;
  }
}
