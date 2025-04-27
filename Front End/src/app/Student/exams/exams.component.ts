import { Component } from '@angular/core';
import { StudentServiceService } from '../../services/student-service.service';
import { NgFor } from '@angular/common';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-exams',
  imports: [NgFor,RouterLink],
  templateUrl: './exams.component.html',
  styleUrl: './exams.component.css'
})
export class ExamsComponent {
  constructor(private service:StudentServiceService){}
exams:any;
userId: number=0
  ngOnInit(){
    this.userId = Number(localStorage.getItem('userid'));
    this.service.listExams().subscribe((data)=>{
      // console.log(data);
      this.exams=data;
      console.log(this.exams);
    });

  }
  startExam(id:number){
    console.log(id);
  } 

}
