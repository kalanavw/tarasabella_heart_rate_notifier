import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {endPoints} from '../pages/model/constants';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private httpService: HttpService) {
  }

  findAllRoles() {
    const endPoint = endPoints.roles;
    return this.httpService.get(endPoint);
  }
}
