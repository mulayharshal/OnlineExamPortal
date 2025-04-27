import { NgIf } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { AdminServiceService } from '../../services/admin-service.service';
import { Router, RouterLink } from '@angular/router';

@Component({
  selector: 'app-admin-create-exam',
  imports: [FormsModule,RouterLink],
  templateUrl: './admin-create-exam.component.html',
  styleUrl: './admin-create-exam.component.css'
})
export class AdminCreateExamComponent {

  exam = {
    title: '',
    description: '',
    duration: 0
  };

  userId: number = Number(localStorage.getItem('userid')); 
  response: string = '';

  constructor(private examService: AdminServiceService, private router: Router) {}

  createExam() {
    console.log(this.userId);
    this.examService.createExam(this.userId, this.exam).subscribe({
      next: (res) => {
        this.response = 'Exam created successfully!';
        console.log('Exam Created:', res);

        // Redirect to another page (e.g., exam list page)
        this.router.navigate(['/admin/exams']); // Update this path as needed
      },
      error: (err) => {
        this.response = 'Error creating exam.';
        console.error('Error:', err);
      }
    });
  }
}
