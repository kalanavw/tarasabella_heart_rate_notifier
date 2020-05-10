import {Injectable} from '@angular/core';
import {HTTP_INTERCEPTORS, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from '@angular/common/http';
import {Store} from '../pages/model/Store';
import {Observable} from 'rxjs';
import {AUTH} from '../pages/model/constants';

@Injectable({
  providedIn: 'root'
})
export class AuthInterceptorService implements HttpInterceptor {
  constructor(private store: Store) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if (this.store.getData(AUTH.token)) {
      req = req.clone({
        setHeaders: {
          Authorization: 'Bearer ' + this.store.getData(AUTH.token)
        }
      });
    }
    return next.handle(req);
  }
}

export let AuthInterceptorProvider = {
  provide: HTTP_INTERCEPTORS,
  useClass: AuthInterceptorService,
  multi: true
};
