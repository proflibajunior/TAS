import { environment } from './../../environments/environment';

import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { SecaoEntity } from './secao.service';

@Injectable({
  providedIn: 'root'
})
export class EleitorService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/eleitores');
  }

  /**
   * Verifica se existe um ID no eleitor passado por parametro.
   * Se existir, significa que o eleitor deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param eleitor 
   */
  public salvar(eleitor: EleitorEntity) {
    if (eleitor.id) {
      return this.alterar(eleitor);
    } else {
      return this.adicionar(eleitor);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/eleitores/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param eleitor 
   */
  private adicionar(eleitor: EleitorEntity) {
    return this.http.post(environment.urlSRV +'/api/eleitores', eleitor);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param eleitor 
   */
  private alterar(eleitor: EleitorEntity) {
    return this.http.put(environment.urlSRV +'/api/eleitores/'+ eleitor.id, eleitor);
  }
}

export class Pessoa {
  id: number;
  codigo: string;
  nome: string;
  email: string;
  endereco: string;
  ativo: string;

  constructor() {
    this.ativo = 'S';
  }
}

export class EleitorEntity extends Pessoa {
  titulo: string;
  secao: SecaoEntity;
}