import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GrupoEntity } from './grupo.service';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/produtos');
  }

  /**
   * Verifica se existe um ID no produto passado por parametro.
   * Se existir, significa que o produto deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param produto 
   */
  public salvar(produto: ProdutoEntity) {
    if (produto.id) {
      return this.alterar(produto);
    } else {
      return this.adicionar(produto);
    }
  }

  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/produtos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param produto 
   */
  private adicionar(produto: ProdutoEntity) {
    return this.http.post(environment.urlSRV +'/api/produtos', produto);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param produto 
   */
  private alterar(produto: ProdutoEntity) {
    return this.http.put(environment.urlSRV +'/api/produtos/'+ produto.id, produto);
  }
}

export class ProdutoEntity {
  id: number;
  codigo: string;
  nome: string;
  descricao: string;
  preco: number;
  ativo: string;
  grupo: GrupoEntity;

  constructor() {
    this.ativo = 'S';
  }
}
