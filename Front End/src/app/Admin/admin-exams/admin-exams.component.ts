import { Component } from '@angular/core';
import { AdminServiceService } from '../../services/admin-service.service';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-exams',
  imports: [NgFor,RouterLink],
  templateUrl: './admin-exams.component.html',
  styleUrl: './admin-exams.component.css'
})
export class AdminExamsComponent {
constructor(private service:AdminServiceService){}

  exams:any;
  
  userId: number = Number(localStorage.getItem('userid'));
  ngOnInit(){

  
    this.service.listAdminExams(this.userId).subscribe((data)=>{
      // console.log(data);
      this.exams=data;
      console.log(this.exams);
    });

  }

}
