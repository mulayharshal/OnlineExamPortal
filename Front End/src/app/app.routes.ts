import { Routes } from '@angular/router';
import { RegisterComponent } from './Auth/register/register.component';
import { LoginComponent } from './Auth/login/login.component';
import { ExamsComponent } from './Student/exams/exams.component';
import { ExamComponent } from './Student/exam/exam.component';
import { ResultsComponent } from './Student/results/results.component';
import { AdminExamsComponent } from './Admin/admin-exams/admin-exams.component';
import { AdminCreateExamComponent } from './Admin/admin-create-exam/admin-create-exam.component';
import { AdminExamresultsComponent } from './Admin/admin-examresults/admin-examresults.component';
import { AdminQuationsComponent } from './Admin/admin-quations/admin-quations.component';
import { AdminAddQuationComponent } from './Admin/admin-add-quation/admin-add-quation.component';
import { AdminEditQuationComponent } from './Admin/admin-edit-quation/admin-edit-quation.component';
import { AdminUpdateExamComponent } from './Admin/admin-update-exam/admin-update-exam.component';

export const routes: Routes = [
    {path:'', component:LoginComponent},
    {path:'register', component:RegisterComponent},
    {path:'login', component:LoginComponent},

    {path:'exams', component:ExamsComponent},
    {path:'exam/:id', component:ExamComponent},
    { path: 'results/:userId', component:ResultsComponent },

    {path:'admin/exams', component:AdminExamsComponent},
    {path:'admin/createExam/:userId', component:AdminCreateExamComponent},
    { path: 'admin/results/:examId', component:AdminExamresultsComponent },
    { path: 'admin/quations/:examId', component:AdminQuationsComponent },
    { path: 'admin/addquation/:examId', component:AdminAddQuationComponent },
    { path: 'admin/editquation/:examId/:quationId', component:AdminEditQuationComponent },
    {path:'admin/update/:userId/:examId', component:AdminUpdateExamComponent},
];
