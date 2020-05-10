import {Component} from '@angular/core';
import {BreakpointObserver, Breakpoints} from '@angular/cdk/layout';
import {Observable, of} from 'rxjs';
import {map, shareReplay} from 'rxjs/operators';
import {Store} from '../model/Store';
import {Router} from '@angular/router';
import {AUTH} from '../model/constants';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );
  userName = '';

  constructor(private breakpointObserver: BreakpointObserver,
              private store: Store,
              private router: Router) {
    if (store.getData(AUTH.username)) {
      this.userName = store.getData(AUTH.username);
    }
    this.isHandset$ = of(true);
  }

  logOut() {
    this.store.clearStore();
    this.router.navigate(['/']);
  }
}
