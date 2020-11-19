import { environment } from './../../environments/environment';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class PartidoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/partidos');
  }

  /**
   * Verifica se existe um ID no partido passado por parametro.
   * Se existir, significa que o partido deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param partido 
   */
  public salvar(partido: PartidoEntity) {
    if (partido.id) {
      return this.alterar(partido);
    } else {
      return this.adicionar(partido);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/partidos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param partido 
   */
  private adicionar(partido: PartidoEntity) {
    return this.http.post(environment.urlSRV +'/api/partidos', partido);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param partido 
   */
  private alterar(partido: PartidoEntity) {
    return this.http.put(environment.urlSRV +'/api/partidos/'+ partido.id, partido);
  }
}

export class PartidoEntity {
  id: number;
  nome: string;
  legenda: string;
}