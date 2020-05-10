import {Component, OnInit, ViewChild} from '@angular/core';
import {UserService} from '../../services/user.service';
import {EsResponse} from '../model/es-response';
import {Store} from '../model/Store';
import {AUTH, ES_RES_STATUS_OK} from '../model/constants';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {environment} from '../../../environments/environment';
import {HeartRateService} from '../../services/heart-rate.service';
import {MatSnackBar} from '@angular/material/snack-bar';
import {MatDialog} from '@angular/material/dialog';
import {ViewHeartRateDataComponent} from './view-heart-rate-data/view-heart-rate-data.component';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {

  userDisplayedColumns: string[] = ['id', 'name', 'contactNo', 'observe', 'view'];
  heartDisplayedColumns: string[] = ['id', 'name', 'rate', 'date', 'status', 'view'];
  userDataSource = new MatTableDataSource<any>([]);
  heartDataSource = new MatTableDataSource<any>([]);
  @ViewChild('userPaginator', {static: true}) userPaginator: MatPaginator;
  @ViewChild('heartPaginator', {static: true}) heartPaginator: MatPaginator;
  hideGrid = false;
  heartRate: any[] = [];


  constructor(private userService: UserService,
              private heartRateService: HeartRateService,
              private snackBar: MatSnackBar,
              private dialog: MatDialog,
              private store: Store) {
  }

  ngOnInit(): void {
    this.loadUsers();
    this.userDataSource.paginator = this.userPaginator;
    this.heartDataSource.paginator = this.heartPaginator;
  }

  loadUsers() {
    let params = {};
    const roles = this.store.getData(AUTH.roles);
    if (roles && !roles.includes('ADMIN')) {
      params = {
        username: this.store.getData(AUTH.username)
      };
    }
    this.userService.findUsers(params).subscribe((res: EsResponse) => {
      console.log(res);
      this.userDataSource.data = res.data;
    });
  }

  viewHeartRateData(element: any) {
    this.hideGrid = true;
    this.findAllHeartRates(element);
  }

  onObserve(user: any) {
    const totalRateCount = environment.totalRateCount[Math.floor(Math.random() * environment.totalRateCount.length)];
    const body = {
      totalRateCount,
      user,
    };
    this.heartRateService.onObserve(body).subscribe((res: EsResponse) => {
      console.log(res);
      if (res.status === ES_RES_STATUS_OK) {
        this.snackBar.open('Heart rate observation success', '', {
          duration: 3000
        }).afterOpened().subscribe(() => {
          this.hideGrid = true;
          this.findAllHeartRates(user);
          const count = res.data.totalRateCount;
          if (count >= 160) {
            alert('Yor heart is at risk');
          }
        });
      } else {
        this.snackBar.open('Heart rate observation failed', '', {
          duration: 3000
        });
      }
    });
  }

  findAllHeartRates(user: any) {
    this.heartRate = [];
    this.heartDataSource.data = [];
    const params = {
      username: user.username
    };
    this.heartRateService.findAllHeartRates(params).subscribe((res: EsResponse) => {
      this.heartRate = res.data;
      this.heartRate.forEach(value => value.name = user.name);
      this.heartDataSource.data = this.heartRate;
    });
  }

  openDialog(element: any) {
    const dialogRef = this.dialog.open(ViewHeartRateDataComponent, {
      width: '800px',
      data: {name: element.name, rateData: element.heartRateData},
    });
  }
}
