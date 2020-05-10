import {Injectable} from '@angular/core';
import {HttpService} from './http.service';
import {endPoints} from '../pages/model/constants';

@Injectable({
  providedIn: 'root'
})
export class HeartRateService {

  constructor(private httpService: HttpService) {
  }

  onObserve(body: { totalRateCount: number; user: any }) {
    const endPoint = endPoints.heartRates;
    return this.httpService.post(endPoint, body);
  }

  findAllHeartRates(params: {}) {
    const endPoint = endPoints.heartRates;
    return this.httpService.get(endPoint, params);
  }
}
