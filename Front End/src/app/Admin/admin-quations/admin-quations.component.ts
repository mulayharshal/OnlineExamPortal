import { Component } from '@angular/core';
import { AdminServiceService } from '../../services/admin-service.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-admin-quations',
  imports: [NgFor,RouterLink,NgIf],
  templateUrl: './admin-quations.component.html',
  styleUrl: './admin-quations.component.css'
})
export class AdminQuationsComponent {
  quations: any[] = [];  // Array of questions
  // userId: number = Number(localStorage.getItem('userid')); // Logged-in user ID
  // selectedAnswers: Map<number, string> = new Map(); // To store answers by question ID

  constructor(
    private service: AdminServiceService,
    private route: ActivatedRoute,
    
  ) {}
   examId :any=0;
  ngOnInit() {
     this.examId = Number(this.route.snapshot.paramMap.get('examId'));  
    console.log(this.examId);
    this.service.listQuations(this.examId).subscribe({
      next: (data: any) => {
        this.quations = data;  // Store questions from the API
        console.log(this.quations);  // For debugging
      },
      error: (err) => {
        console.error('Error fetching questions', err);  // Handle any errors
      }
    });
  }

  deleteQuestion(questionId: number) {
      this.service.deleteQuation(questionId).subscribe(
          (response) => {
            alert('Question deleted successfully!');
            window.location.reload();
          },
          (error) => {
            console.error('Error deleting question', error);
            alert('Failed to delete the question');
          }
        );
    
  }

}
