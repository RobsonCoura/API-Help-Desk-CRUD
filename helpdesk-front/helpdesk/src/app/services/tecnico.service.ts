import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { API_CONFIG } from '../config/api.config';
import { Observable } from 'rxjs';
import { Tecnico } from '../models/tecnico';

// Decorador Injectable para indicar que o serviço pode ser injetado em outros componentes e serviços
@Injectable({
  providedIn: 'root'  // O serviço é registrado no nível raiz, tornando-o disponível em toda a aplicação
})
export class TecnicoService {
  constructor(private http: HttpClient) { } // Construtor para injeção do HttpClient
  // Método para buscar todos os técnicos
  findAll(): Observable<Tecnico[]> {
    return this.http.get<Tecnico[]>(`${API_CONFIG.BASE_URL}/tecnicos`); // Faz uma solicitação GET para a API e retorna um Observable contendo a lista de técnicos
  }
}
