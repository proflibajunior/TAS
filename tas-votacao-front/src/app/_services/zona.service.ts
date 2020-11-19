import { environment } from './../../environments/environment';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ZonaService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/zonas');
  }

  /**
   * Verifica se existe um ID no zona passado por parametro.
   * Se existir, significa que o zona deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param zona 
   */
  public salvar(zona: ZonaEntity) {
    if (zona.id) {
      return this.alterar(zona);
    } else {
      return this.adicionar(zona);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/zonas/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param zona 
   */
  private adicionar(zona: ZonaEntity) {
    return this.http.post(environment.urlSRV +'/api/zonas', zona);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param zona 
   */
  private alterar(zona: ZonaEntity) {
    return this.http.put(environment.urlSRV +'/api/zonas/'+ zona.id, zona);
  }
}

export class ZonaEntity {
  id: number;
  codigo: string;
  nome: string;
}