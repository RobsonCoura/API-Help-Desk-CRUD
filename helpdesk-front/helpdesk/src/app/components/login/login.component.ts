// Importações e configurações de módulos
import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Toast, ToastrService } from 'ngx-toastr';
import { Credenciais } from 'src/app/models/credenciais';

// Definição do componente com seletor, template e estilos
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  // Inicialização das credenciais
  creds: Credenciais = {
    email: '',
    senha: ''
  };

  // Definição dos controles de formulário com validações
  email = new FormControl(null, Validators.email);
  senha = new FormControl(null, Validators.minLength(3));

  // Injeta o serviço ToastrService para notificações
  constructor(private toast: ToastrService) { }

  ngOnInit(): void {
    // Inicialização adicional, se necessário
  }

  // Método para realizar login
  logar() {
    // Exibe uma mensagem de erro e limpa a senha
    this.toast.error('Usuário e/ou senha inválidos!', 'Login');
    this.creds.senha = '';
  }

  // Valida se os campos de email e senha são válidos
  validaCampos(): boolean {
    return this.email.valid && this.senha.valid;
  }
}