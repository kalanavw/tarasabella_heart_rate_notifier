import { Injectable } from '@angular/core';
import {HttpService} from './http.service';
import {endPoints} from '../pages/model/constants';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpService: HttpService) {
  }

  findUsers(param: any) {
    const endPoint = endPoints.users;
    return this.httpService.get(endPoint, param);
  }
}
