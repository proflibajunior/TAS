import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class GrupoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/grupos');
  }

  /**
   * Verifica se existe um ID no grupo passado por parametro.
   * Se existir, significa que o grupo deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param grupo 
   */
  public salvar(grupo: GrupoEntity) {
    if (grupo.id) {
      return this.alterar(grupo);
    } else {
      return this.adicionar(grupo);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/grupos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param grupo 
   */
  private adicionar(grupo: GrupoEntity) {
    return this.http.post(environment.urlSRV +'/api/grupos', grupo);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param grupo 
   */
  private alterar(grupo: GrupoEntity) {
    return this.http.put(environment.urlSRV +'/api/grupos/'+ grupo.id, grupo);
  }
}

export class GrupoEntity {
  id: number;
  nome: string;
}
