import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Toast, ToastrService } from 'ngx-toastr';
import { Credenciais } from 'src/app/models/credenciais';

// Define um componente Angular
@Component({
  selector: 'app-login', // Seletor do componente
  templateUrl: './login.component.html', // Template HTML do componente
  styleUrls: ['./login.component.css'] // Arquivo de estilos CSS do componente
})
export class LoginComponent implements OnInit {

  // Define um objeto de credenciais inicializado com valores vazios
  creds: Credenciais = {
    email: '',
    senha: ''
  }

  // Define um controle de formulário para o email com validação de email
  email = new FormControl(null, Validators.email);
  // Define um controle de formulário para a senha com validação de comprimento mínimo de 3 caracteres
  senha = new FormControl(null, Validators.minLength(3));

  // Construtor injeta o serviço de notificação ToastrService
  constructor(private toast: ToastrService) { }

  // Método ngOnInit é chamado após a inicialização do componente
  ngOnInit(): void {
  }

  // Método para realizar o login
  logar() {
    // Exibe uma mensagem de erro se as credenciais forem inválidas
    this.toast.error('Usuário e/ou senha inválidos!', 'Login');
    // Reseta a senha no objeto de credenciais
    this.creds.senha = '';
  }

  // Método para validar se os campos de email e senha são válidos
  validaCampos(): boolean {
    // Retorna true se ambos os campos forem válidos, caso contrário retorna false
    if (this.email.valid && this.senha.valid) {
      return true;
    } else {
      return false;
    }
  }
}
