import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {
  constructor(private http: HttpClient) {}

  // private baseUrl: string = 'http://localhost:8080';
  private baseUrl: string = 'https://onlineexamportalbackend.up.railway.app';

  // List all exams
  listExams() {
    return this.http.get(`${this.baseUrl}/student/exams`);
  }

  // List questions for a specific exam
  listQuations(id: number) {
    return this.http.get(`${this.baseUrl}/student/exam/${id}`);
  }

  // Submit answers for the exam
  submitExam(examId: number, userId: number, answers: any[]) {
    const submissionData = {
      examId: examId,
      userId: userId,
      answers: answers
    };
    return this.http.post(`${this.baseUrl}/student/submit-exam/${examId}`, submissionData);
  }

  getResults(userId: number) {
    return this.http.get(`${this.baseUrl}/student/results/${userId}`);
  }

  getExamData(examId: number) {
    return this.http.get<any>(`${this.baseUrl}/student/exam/data/${examId}`)
  }
  
}
