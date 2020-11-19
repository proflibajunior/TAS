import { Pessoa } from './../../../../tas-webapp/src/app/_services/cliente.service';
import { environment } from './../../environments/environment';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { PartidoEntity } from './partido.service';

@Injectable({
  providedIn: 'root'
})
export class CandidatoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/candidatos');
  }

  /**
   * Verifica se existe um ID no candidato passado por parametro.
   * Se existir, significa que o candidato deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param candidato 
   */
  public salvar(candidato: CandidatoEntity) {
    if (candidato.id) {
      return this.alterar(candidato);
    } else {
      return this.adicionar(candidato);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/candidatos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param candidato 
   */
  private adicionar(candidato: CandidatoEntity) {
    return this.http.post(environment.urlSRV +'/api/candidatos', candidato);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param candidato 
   */
  private alterar(candidato: CandidatoEntity) {
    return this.http.put(environment.urlSRV +'/api/candidatos/'+ candidato.id, candidato);
  }
}

export class CandidatoEntity extends Pessoa {
  numero: string;
  partido: PartidoEntity;
}