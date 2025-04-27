import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  


  constructor(private http: HttpClient) {}
  private baseUrl: string = 'http://localhost:8080';

  listAdminExams(id: number) {
    return this.http.get(`${this.baseUrl}/admin/exams/${id}`);
  }

  createExam(userId: number, exam: any) {
    return this.http.post(`http://localhost:8080/admin/create-exam/${userId}`, exam);
  }

  getResults(examId: number) {
    return this.http.get(`http://localhost:8080/admin/results/${examId}`);
  }
  listQuations(id: number) {
    return this.http.get(`http://localhost:8080/student/exam/${id}`);
  }

  addQuestion(examId: number, questionData: any) {
    return this.http.post(`http://localhost:8080/admin/add-question/${examId}`, questionData);
  }
  updateQuestion(questionId: number, question: any){
   return this.http.put(`http://localhost:8080/admin/update-question/${questionId}`, question)
  }
  deleteQuation(questionId: number){
    return this.http.delete(`http://localhost:8080/admin/delete-question/${questionId}`)
  }
  getExamDetails(examId: number){
    return this.http.get<any>(`http://localhost:8080/admin/get-exam/${examId}`)
  }

  updateExam(examId: number, exam: any){
    return this.http.put<any>(`http://localhost:8080/admin/update-exam/${examId}`, exam)
  }

  getQuestionById(questionId: number) {
    return this.http.get<any>(`http://localhost:8080/admin/get-question/${questionId}`)
  }
 
  

  
}
