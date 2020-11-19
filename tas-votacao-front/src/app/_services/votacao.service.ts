import { environment } from './../../environments/environment';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CandidatoEntity } from './candidato.service';
import { EleitorEntity } from './eleitor.service';

@Injectable({
  providedIn: 'root'
})
export class VotacaoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/votacoes');
  }

  /**
   * Verifica se existe um ID no votacao passado por parametro.
   * Se existir, significa que o votacao deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param votacao 
   */
  public salvar(votacao: VotacaoEntity) {
    if (votacao.id) {
      return this.alterar(votacao);
    } else {
      return this.adicionar(votacao);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/votacoes/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param votacao 
   */
  private adicionar(votacao: VotacaoEntity) {
    return this.http.post(environment.urlSRV +'/api/votacoes', votacao);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param votacao 
   */
  private alterar(votacao: VotacaoEntity) {
    return this.http.put(environment.urlSRV +'/api/votacoes/'+ votacao.id, votacao);
  }
}

export class VotacaoEntity {
  id: number;
  dthrvotacao: Date;
  eleitor: EleitorEntity;
  candidato: CandidatoEntity;

  constructor() {
    this.dthrvotacao = new Date();
  }
}