import { GrupoService } from './../_services/grupo.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-grupo',
  templateUrl: './grupo.component.html',
  styleUrls: ['./grupo.component.scss']
})
export class GrupoComponent implements OnInit {

  public grupos = [];

  constructor(private service: GrupoService) { }

  ngOnInit(): void {
    //Carrega todos os grupos
    this.service.listar().subscribe(result => {
      this.grupos = result as [];
    }, error => {
      //TO-DO: Tratar erro
    })
  }

}
