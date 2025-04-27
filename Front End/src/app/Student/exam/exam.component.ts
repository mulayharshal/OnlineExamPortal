import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentServiceService } from '../../services/student-service.service';
import { FormsModule } from '@angular/forms';
import { NgFor, NgIf } from '@angular/common';
import { interval, Subscription } from 'rxjs';
import { takeWhile } from 'rxjs/operators';

@Component({
  selector: 'app-exam',
  imports: [FormsModule, NgFor, NgIf],
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit, OnDestroy {
  quations: any[] = [];  // Array of questions
  userId: number = Number(localStorage.getItem('userid')); // Logged-in user ID
  selectedAnswers: Map<number, string> = new Map(); // To store answers by question ID
  examdata: any;
  // Timer properties
  timeLeft: number = 0; // Initialize to 0
  timerSubscription: Subscription | null = null;
  timerDisplay: string = '';

  constructor(
    private service: StudentServiceService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit() {
    const examId = Number(this.route.snapshot.paramMap.get('id'));  // Get exam ID from route params

    this.service.listQuations(examId).subscribe({
      next: (data: any) => {
        this.quations = data;  // Store questions from the API
        console.log(this.quations);  // For debugging
      },
      error: (err) => {
        console.error('Error fetching questions', err);  // Handle any errors
      }
    });

    this.service.getExamData(examId).subscribe({
      next: (data: any) => {
        this.examdata = data;  // Store exam data from the API
        console.log(this.examdata);  // For debugging
        this.timeLeft = this.examdata.duration * 60;  // Set timeLeft after examdata is fetched
        this.startTimer();  // Start the timer after exam data is loaded
      },
      error: (err) => {
        console.error('Error fetching exam data', err);  // Handle any errors
      }
    });
  }

  ngOnDestroy() {
    // Clean up timer subscription when component is destroyed
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
  }

  // Start the countdown timer
  startTimer() {
    this.updateTimerDisplay();
    this.timerSubscription = interval(1000)
      .pipe(takeWhile(() => this.timeLeft > 0))
      .subscribe(() => {
        this.timeLeft -= 1;
        this.updateTimerDisplay();
        
        if (this.timeLeft === 0) {
          this.onTimeUp();
        }
      });
  }

  // Format and update the timer display
  updateTimerDisplay() {
    const minutes = Math.floor(this.timeLeft / 60);
    const seconds = this.timeLeft % 60;
    this.timerDisplay = `${minutes.toString().padStart(2, '0')}:${seconds.toString().padStart(2, '0')}`;
  }

  // Handle time up event
  onTimeUp() {
    alert('Time is up! Your exam will be submitted automatically.');
    this.onSubmit();
  }

  // Handle selection of an option for a question
  onOptionSelect(questionId: number, selectedOption: string) {
    this.selectedAnswers.set(questionId, selectedOption);  // Store selected answer for the question
  }

  // On form submission
  onSubmit() {
    if (this.timerSubscription) {
      this.timerSubscription.unsubscribe();
    }
    
    const examId = Number(this.route.snapshot.paramMap.get('id'));  // Get exam ID from URL
    const answers = Array.from(this.selectedAnswers).map(([questionId, selectedOption]) => ({
      questionId,
      selectedOption
    }));
    console.log("Selected Answers: ", answers);  // Log selected answers for debugging

    // Submit the exam to backend
    this.service.submitExam(examId, this.userId, answers).subscribe(
      (response) => {
        console.log('Exam submitted successfully', response);
        // Redirect to the results page or display success message
        this.router.navigate(['/results', this.userId]);
      },
      (error) => {
        console.error('Error submitting exam', error);  // Handle submission errors
      }
    );
  }
}