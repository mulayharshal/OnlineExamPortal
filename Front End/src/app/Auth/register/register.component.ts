import { Component } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthServiceService } from '../../services/auth-service.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-register',
  imports: [FormsModule, RouterLink],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  
  constructor(private service:AuthServiceService, private router: Router) { }

  userData:any;  
  addUser(userData:any) {
    console.log(userData);
    this.service.addUser(userData).subscribe(
      (response) => {
        console.log("reigister sucess.....");
        console.log(response);
        this.router.navigate(['/login']);
       
        },
        (error) => {
          console.error(error);
          }
        )
  }

}
