import { ZonaEntity } from './zona.service';
import { environment } from './../../environments/environment';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SecaoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/secoes');
  }

  /**
   * Verifica se existe um ID no secao passado por parametro.
   * Se existir, significa que o secao deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param secao 
   */
  public salvar(secao: SecaoEntity) {
    if (secao.id) {
      return this.alterar(secao);
    } else {
      return this.adicionar(secao);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/secoes/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param secao 
   */
  private adicionar(secao: SecaoEntity) {
    return this.http.post(environment.urlSRV +'/api/secoes', secao);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param secao 
   */
  private alterar(secao: SecaoEntity) {
    return this.http.put(environment.urlSRV +'/api/secoes/'+ secao.id, secao);
  }
}

export class SecaoEntity {
  id: number;
  codigo: string;
  zona: ZonaEntity;
}