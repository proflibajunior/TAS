
import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Pessoa } from './cliente.service';

@Injectable({
  providedIn: 'root'
})
export class FornecedorService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/fornecedores');
  }

  /**
   * Verifica se existe um ID no fornecedor passado por parametro.
   * Se existir, significa que o fornecedor deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param fornecedor 
   */
  public salvar(fornecedor: FornecedorEntity) {
    if (fornecedor.id) {
      return this.alterar(fornecedor);
    } else {
      return this.adicionar(fornecedor);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/fornecedores/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param fornecedor 
   */
  private adicionar(fornecedor: FornecedorEntity) {
    return this.http.post(environment.urlSRV +'/api/fornecedores', fornecedor);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param fornecedor 
   */
  private alterar(fornecedor: FornecedorEntity) {
    return this.http.put(environment.urlSRV +'/api/fornecedores/'+ fornecedor.id, fornecedor);
  }
}
export class FornecedorEntity extends Pessoa {
  contato: string;
  cnpj: string;
}
