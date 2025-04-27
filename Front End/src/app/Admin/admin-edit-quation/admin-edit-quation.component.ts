import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { AdminServiceService } from '../../services/admin-service.service';
import { FormsModule, NgModel } from '@angular/forms';
import { ActivatedRoute, Route, Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-edit-quation',
  imports: [FormsModule, RouterLink],
  templateUrl: './admin-edit-quation.component.html',
  styleUrl: './admin-edit-quation.component.css'
})
export class AdminEditQuationComponent {


  questionId: number = 0;  // Set the ID you want to update
  question = {
    content: '',
    optionA: '',
    optionB: '',
    optionC: '',
    optionD: '',
    correctAnswer: ''
  };

  constructor(private service: AdminServiceService, private route: ActivatedRoute,private router: Router) { }
  examId :any=0;
  quationId: any=0;
  ngOnInit(){
    this.examId=Number(this.route.snapshot.paramMap.get('examId')); 
    this.quationId=Number(this.route.snapshot.paramMap.get('quationId')); 
    this.loadQuestion();
  }

  loadQuestion() {
    // Replace with your actual service call to get question data\
    console.log("quation id is :", this.quationId);
    this.service.getQuestionById(this.quationId).subscribe(
      (data) => {
        this.question = data;
        // If using reactive forms, you would patch the values here:
        // this.questionForm.patchValue(data);
      },
      (error) => {
        console.error('Error loading question:', error);
      }
    );
  }
  updateQuestion() {

    console.log(this.quationId);
    console.log(this.question)
    this.service.updateQuestion(this.quationId , this.question).subscribe(
        (response: any) => {
          console.log('Question updated successfully', response);
          alert(response.message);
          this.router.navigate(['/admin/quations/', this.examId]);
          
        },
        (error) => {
          console.error('Error updating question', error);
          alert('Failed to update question.');
        }
      );
  
  }
}
