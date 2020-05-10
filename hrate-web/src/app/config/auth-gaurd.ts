import {Injectable} from '@angular/core';
import {CanActivate, Router} from '@angular/router';
import {Store} from '../pages/model/Store';
import {AUTH} from '../pages/model/constants';

@Injectable()
export class AuthGuard implements CanActivate {

    constructor(private router: Router,
                private store: Store) {
    }

    canActivate() {
        if (this.store.getData(AUTH.token)) {
            return true;
        }
        this.router.navigate(['/']);
        return false;
    }
}
