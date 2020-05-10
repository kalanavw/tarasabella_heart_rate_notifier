import {Component, Inject, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MAT_DIALOG_DATA} from '@angular/material/dialog';

@Component({
  selector: 'app-view-heart-rate-data',
  templateUrl: './view-heart-rate-data.component.html',
  styleUrls: ['./view-heart-rate-data.component.css']
})
export class ViewHeartRateDataComponent implements OnInit {

  heartRateDisplayedColumns: string[] = ['id', 'count', 'time'];
  heartRateDataSource = new MatTableDataSource<any>([]);
  @ViewChild(MatPaginator, {static: true}) heartPaginator: MatPaginator;

  constructor(@Inject(MAT_DIALOG_DATA) public data: {name: string, rateData: any[]}) { }

  ngOnInit(): void {
    this.data.rateData.sort((a, b) => (a.value > b.value) ? 1 : -1);
    this.heartRateDataSource.data = this.data.rateData;
    this.heartRateDataSource.paginator = this.heartPaginator;
  }

}
