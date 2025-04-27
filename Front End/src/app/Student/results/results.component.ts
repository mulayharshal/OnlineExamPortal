import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { StudentServiceService } from '../../services/student-service.service';
import { CommonModule, NgFor, NgIf } from '@angular/common';

@Component({
  selector: 'app-results',
  templateUrl: './results.component.html',
  styleUrls: ['./results.component.css'],
  imports:[NgFor, NgIf, CommonModule, RouterLink]
})
export class ResultsComponent implements OnInit {
  results: any = []; // Variable to store the exam results

  constructor(private service: StudentServiceService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    // Assuming the userId is passed as a route parameter (you can replace this with your logic)
    const userId = Number(this.route.snapshot.paramMap.get('userId'));
    this.service.getResults(userId).subscribe((data) => {
      this.results = data;
      console.log(this.results); // Log the results to see the structure
    });
  }
}
