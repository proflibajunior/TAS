import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/clientes');
  }

  /**
   * Verifica se existe um ID no cliente passado por parametro.
   * Se existir, significa que o cliente deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param cliente 
   */
  public salvar(cliente: ClienteEntity) {
    if (cliente.id) {
      return this.alterar(cliente);
    } else {
      return this.adicionar(cliente);
    }
  }

  /**
   * Exclui a cidade com o mesmo ID passado por parâmetro
   * 
   * @param id 
   */
  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/clientes/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param cliente 
   */
  private adicionar(cliente: ClienteEntity) {
    return this.http.post(environment.urlSRV +'/api/clientes', cliente);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param cliente 
   */
  private alterar(cliente: ClienteEntity) {
    return this.http.put(environment.urlSRV +'/api/clientes/'+ cliente.id, cliente);
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
export class ClienteEntity extends Pessoa {
  cpf: string;
}
