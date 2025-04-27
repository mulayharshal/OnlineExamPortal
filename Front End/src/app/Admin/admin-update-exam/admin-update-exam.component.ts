import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { AdminServiceService } from '../../services/admin-service.service';

@Component({
  selector: 'app-admin-update-exam',
  imports: [FormsModule, RouterLink],
  templateUrl: './admin-update-exam.component.html',
  styleUrl: './admin-update-exam.component.css'
})
export class AdminUpdateExamComponent {

  examId!: number;
  exam = {
    title: '',
    description: '',
    duration: 0
  };

  constructor(
    private route: ActivatedRoute,
    private http: HttpClient,
    private router: Router,
    private service: AdminServiceService,
  ) {}

  ngOnInit(): void {
    this.examId = Number(this.route.snapshot.paramMap.get('examId'));
    this.getExamDetails();
  }

  getExamDetails() {
    // this.http.get<any>(`http://localhost:8080/admin/get-exam/${this.examId}`)
    this.service.getExamDetails(this.examId).subscribe({
        next: (data) => {
          this.exam = data;
        },
        error: (error) => {
          console.error('Error fetching exam details', error);
        }
      });
  }

  updateExam() {
    // this.http.put<any>(`http://localhost:8080/admin/update-exam/${this.examId}`, this.exam)
    this.service.updateExam(this.examId, this.exam).subscribe({
        next: (data) => {
          alert('Exam updated successfully!');
          this.router.navigate(['/admin/exams']); // Redirect after update
        },
        error: (error) => {
          console.error('Error updating exam', error);
        }
      });
  }
}
