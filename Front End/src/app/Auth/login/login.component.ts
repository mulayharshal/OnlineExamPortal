import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AuthServiceService } from '../../services/auth-service.service';
import { Router } from '@angular/router';
import { NgIf } from '@angular/common';


@Component({
  selector: 'app-login',
  imports: [FormsModule,NgIf],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
  constructor(private service:AuthServiceService, private router: Router) { }

  loginUser(formData: any) {
    console.log(formData);
    this.service.loginUser(formData).subscribe(
      (response) => {
        console.log("login sucess.....");
        console.log(response);
        if((response as any).status== 'SUCCESS'){
          console.log((response as any).res.role);
          localStorage.setItem('userrole',(response as any).res.role);
          localStorage.setItem('userid',(response as any).res.id);
          if((response as any).res.role=='ADMIN'){
            console.log("admins");
            this.router.navigate(['/admin/exams']);
          }else{
            console.log("users");
              this.router.navigate(['/exams']);
          }
        }else{
          console.log(response);
          console.log((response as any).status);
          alert((response as any).res);
        }
        
        

        },
        (error) => {
          // console.error(error.res);
          // console.log((error as any).res);
          // alert((error as any).res);
          }
        )
  }
  
}
