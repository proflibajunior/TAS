import { ZonaComponent } from './zona/zona.component';
import { VotacaoComponent } from './votacao/votacao.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CandidatoComponent } from './candidato/candidato.component';
import { EleitorComponent } from './eleitor/eleitor.component';
import { PartidoComponent } from './partido/partido.component';
import { SecaoComponent } from './secao/secao.component';

const routes: Routes = [
  {path: 'candidatos', component: CandidatoComponent},
  {path: 'eleitores', component: EleitorComponent},
  {path: 'partidos', component: PartidoComponent},
  {path: 'secoes', component: SecaoComponent},
  {path: 'votacoes', component: VotacaoComponent},
  {path: 'zonas', component: ZonaComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
