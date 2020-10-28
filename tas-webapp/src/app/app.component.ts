import { TokenService } from './_services/token.service';
import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'tas-webapp';

  public loadToken: boolean;
  public erroMessage: string;

  constructor(private tokenService: TokenService) {
    this.loadToken = true;
    this.erroMessage = '';

    this.tokenService.getAccessToken().subscribe(result => {
      
      sessionStorage.setItem('token', result['token']);

    }, error => {
      
      this.erroMessage = error.message;

    }).add(() => {

      this.loadToken = false;
      this.erroMessage = '';

    })

  }
}
