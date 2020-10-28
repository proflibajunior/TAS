import { environment } from './../../environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  constructor(private http: HttpClient) { }

  public getAccessToken() {
    return this.http.post(environment.urlSRV +'/token', {appkey: environment.appKEY});
  }
}
