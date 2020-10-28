import { GrupoService } from './../_services/grupo.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-grupo',
  templateUrl: './grupo.component.html',
  styleUrls: ['./grupo.component.scss']
})
export class GrupoComponent implements OnInit {

  public grupos = [];
  public errorMessage: string;

  constructor(private service: GrupoService) { }

  ngOnInit(): void {
    //Inicia as variáveis
    this.errorMessage = '';

    //Carrega todos os grupos
    this.service.listar().subscribe(result => {
      this.grupos = result as [];
    }, error => {
      this.errorMessage = error.message;
    })
  }

}
