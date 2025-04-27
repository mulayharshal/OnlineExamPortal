import { Component } from '@angular/core';
import { AdminServiceService } from '../../services/admin-service.service';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-admin-examresults',
  imports: [NgFor,NgIf, RouterLink],
  templateUrl: './admin-examresults.component.html',
  styleUrl: './admin-examresults.component.css'
})
export class AdminExamresultsComponent {
  results: any = []; 
  constructor(private service: AdminServiceService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Assuming the userId is passed as a route parameter (you can replace this with your logic)
    const examId = Number(this.route.snapshot.paramMap.get('examId'));
    this.service.getResults(examId).subscribe((data) => {
      this.results = data;
      console.log(this.results); // Log the results to see the structure
    });
  }

  formatDate(dateString: string): string {
    const options: Intl.DateTimeFormatOptions = { 
      year: 'numeric', 
      month: 'short', 
      day: 'numeric',
      hour: '2-digit',
      minute: '2-digit'
    };
    return new Date(dateString).toLocaleDateString(undefined, options);
  }

  calculatePercentage(score: number, total: number): number {
    return total > 0 ? Math.round((score / total) * 100) : 0;
  }
}
