import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AdminServiceService {
  


  constructor(private http: HttpClient) {}
  // private baseUrl: string = 'http://localhost:8080';
  private baseUrl: string = 'https://onlineexamportalbackend.up.railway.app';


  listAdminExams(id: number) {
    return this.http.get(`${this.baseUrl}/admin/exams/${id}`);
  }

  createExam(userId: number, exam: any) {
    return this.http.post(`${this.baseUrl}/admin/create-exam/${userId}`, exam);
  }

  getResults(examId: number) {
    return this.http.get(`${this.baseUrl}/admin/results/${examId}`);
  }
  listQuations(id: number) {
    return this.http.get(`${this.baseUrl}/student/exam/${id}`);
  }

  addQuestion(examId: number, questionData: any) {
    return this.http.post(`${this.baseUrl}/admin/add-question/${examId}`, questionData);
  }
  updateQuestion(questionId: number, question: any){
   return this.http.put(`${this.baseUrl}/admin/update-question/${questionId}`, question)
  }
  deleteQuation(questionId: number){
    return this.http.delete(`${this.baseUrl}/admin/delete-question/${questionId}`)
  }
  getExamDetails(examId: number){
    return this.http.get<any>(`${this.baseUrl}/admin/get-exam/${examId}`)
  }

  updateExam(examId: number, exam: any){
    return this.http.put<any>(`${this.baseUrl}/admin/update-exam/${examId}`, exam)
  }

  getQuestionById(questionId: number) {
    return this.http.get<any>(`${this.baseUrl}/admin/get-question/${questionId}`)
  }
 
  

  
}
