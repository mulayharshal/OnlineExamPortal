import { HttpClient, provideHttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  
  

  
  constructor(private http:HttpClient) {
    provideHttpClient();
   }
// private baseUrl: string = 'http://localhost:8080';
  private baseUrl: string = 'https://onlineexamportalbackend.up.railway.app';


   addUser(userData: any) {
    return this.http.post(`${this.baseUrl}/auth/register`,userData);
  }
  loginUser(formData: any) {
    return this.http.post(`${this.baseUrl}/auth/login`,formData);
  }

}
