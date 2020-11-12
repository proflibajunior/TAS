import { Component, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material/dialog';
import { ItemPedidoEntity } from 'src/app/_services/pedido.service';
import { ProdutoEntity, ProdutoService } from 'src/app/_services/produto.service';

@Component({
  selector: 'app-item-pedido',
  templateUrl: './item-pedido.component.html',
  styleUrls: ['./item-pedido.component.scss']
})
export class ItemPedidoComponent {
  public itemPedido: ItemPedidoEntity;
  public produtos: ProdutoEntity[] = [];
  
  constructor(private produtoService: ProdutoService, public dialogRef: MatDialogRef<ItemPedidoComponent>) { 
    this.itemPedido = new ItemPedidoEntity();

    //Carrego todos os produtos
    this.produtoService.listar().subscribe(result => {
      this.produtos = result as [];
    });
  }

  public onDismiss(): void {
    this.dialogRef.close(false);
  }

  public onConfirm(): void {
    this.dialogRef.close(this.itemPedido);
  }

  public changeItem(): void {
    this.itemPedido.vlrunit = this.itemPedido.produto.preco;
    this.itemPedido.qtdade = 1;
  }

}
