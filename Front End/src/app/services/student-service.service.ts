import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class StudentServiceService {
  constructor(private http: HttpClient) {}

  // List all exams
  listExams() {
    return this.http.get("http://localhost:8080/student/exams");
  }

  // List questions for a specific exam
  listQuations(id: number) {
    return this.http.get(`http://localhost:8080/student/exam/${id}`);
  }

  // Submit answers for the exam
  submitExam(examId: number, userId: number, answers: any[]) {
    const submissionData = {
      examId: examId,
      userId: userId,
      answers: answers
    };
    return this.http.post(`http://localhost:8080/student/submit-exam/${examId}`, submissionData);
  }

  getResults(userId: number) {
    return this.http.get(`http://localhost:8080/student/results/${userId}`);
  }

  getExamData(examId: number) {
    return this.http.get<any>(`http://localhost:8080/student/exam/data/${examId}`)
  }
  
}
