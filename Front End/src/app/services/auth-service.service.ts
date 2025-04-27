import { HttpClient, provideHttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceService {
  
  

  
  constructor(private http:HttpClient) {
    provideHttpClient();
   }


   addUser(userData: any) {
    return this.http.post('http://localhost:8080/auth/register',userData);
  }
  loginUser(formData: any) {
    return this.http.post('http://localhost:8080/auth/login',formData);
  }

}
