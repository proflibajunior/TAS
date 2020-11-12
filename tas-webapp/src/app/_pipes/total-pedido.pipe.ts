import { Pipe, PipeTransform } from '@angular/core';
import { ItemPedidoEntity } from '../_services/pedido.service';

@Pipe({
  name: 'totalPedido',
  pure: false
})
export class TotalPedidoPipe implements PipeTransform {

  transform(items: ItemPedidoEntity[]): number {
    return items.reduce((total, item) => {
      return total + (item.qtdade * item.vlrunit);
    }, 0);
  }

}
