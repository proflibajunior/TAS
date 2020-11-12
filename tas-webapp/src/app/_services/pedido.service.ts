import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { GrupoEntity } from './grupo.service';
import { ProdutoEntity } from './produto.service';
import { ClienteEntity } from './cliente.service';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private http: HttpClient) { }

  /**
   * Fornece uma lista com TODOS os registros
   */
  public listar() {
    return this.http.get(environment.urlSRV +'/api/pedidos');
  }

  /**
   * Verifica se existe um ID no pedido passado por parametro.
   * Se existir, significa que o pedido deverá ser atualizado,
   * caso contrário, deverá ser incluído.
   * 
   * @param pedido 
   */
  public salvar(pedido: PedidoEntity) {
    if (pedido.id) {
      return this.alterar(pedido);
    } else {
      return this.adicionar(pedido);
    }
  }

  public excluir(id: number) {
    return this.http.delete(environment.urlSRV +'/api/pedidos/'+ id);
  }
  
  /**
   * Adiciona um novo registro
   * 
   * @param pedido 
   */
  private adicionar(pedido: PedidoEntity) {
    return this.http.post(environment.urlSRV +'/api/pedidos', pedido);
  }

  /**
   * Altera o registro com o ID passado por parametro na URI
   * @param pedido 
   */
  private alterar(pedido: PedidoEntity) {
    return this.http.put(environment.urlSRV +'/api/pedidos/'+ pedido.id, pedido);
  }
}

export class ItemPedidoEntity {
  id: number;
  qtdade: number;
  vlrunit: number;
  produto: ProdutoEntity;
}

export class PedidoEntity {
  id: number;
  codigo: string;
  dtpedido: Date;
  dtfaturado: Date;
  cliente: ClienteEntity;
  itens: ItemPedidoEntity[];

  constructor() {
    this.itens = [];
  }
}
