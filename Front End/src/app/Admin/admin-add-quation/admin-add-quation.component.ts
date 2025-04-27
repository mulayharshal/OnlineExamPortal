import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { Router } from '@angular/router';
import { AdminServiceService } from '../../services/admin-service.service';

@Component({
  selector: 'app-admin-add-quation',
  templateUrl: './admin-add-quation.component.html',
  styleUrls: ['./admin-add-quation.component.css'], // Fixed the typo
  imports:[FormsModule ,ReactiveFormsModule,RouterLink]
})
export class AdminAddQuationComponent implements OnInit {

  questionForm: FormGroup;
  examId!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private service: AdminServiceService,
    private router: Router
  ) {
    // Define the form group with validation
    this.questionForm = this.fb.group({
      content: ['', Validators.required],
      optionA: ['', Validators.required],
      optionB: ['', Validators.required],
      optionC: ['', Validators.required],
      optionD: ['', Validators.required],
      correctAnswer: ['', [Validators.required, Validators.pattern(/^[ABCD]$/)]], // Validate correct answer to be A, B, C, or D
    });
  }

  ngOnInit(): void {
    // Retrieve examId from URL params
    this.route.params.subscribe(params => {
      this.examId = params['examId']; // Coming from the URL path
    });
  }

  // Submit form handler
  onSubmit() {
    if (this.questionForm.invalid) {
      return;
    }

    const questionData = this.questionForm.value;

    // Call service method to add the question
    console.log(questionData);
    this.service.addQuestion(this.examId, questionData).subscribe({
      next: (res) => {
        console.log('Question created successfully', res);
        this.questionForm.reset(); // Reset the form after successful submission
        this.router.navigate(['/admin/quations', this.examId]); 
      },
      error: (err) => {
        console.error('Error creating question', err); // Log the error
      }
    });
  }
}
