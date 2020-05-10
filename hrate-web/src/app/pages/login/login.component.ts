import {Component, OnInit} from '@angular/core';
import {AuthenticationService} from '../../services/authentication.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {NameCodePair} from '../model/name-code-pair';
import {EsResponse} from '../model/es-response';
import {MatSnackBar} from '@angular/material/snack-bar';
import {AUTH, ES_RES_STATUS_OK} from '../model/constants';
import {Store} from '../model/Store';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loginForm: FormGroup;

  constructor(private authService: AuthenticationService,
              private store: Store,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.loginForm = new FormGroup({
      username: new FormControl('kalana', [Validators.required]),
      password: new FormControl('123', [Validators.required]),
    });
  }

  onLogin() {
    const params: NameCodePair[] = [
      new NameCodePair(0, 'username', this.loginForm.value.username),
      new NameCodePair(0, 'password', this.loginForm.value.password)
    ];
    this.authService.onLogin(params).subscribe((res: EsResponse) => {
      if (res.status === ES_RES_STATUS_OK) {
        const jwt = res.data.token;
        const jwtData = jwt.split('.')[1];
        const decodedJwtJsonData = window.atob(jwtData);
        const decodedJwtData: any = JSON.parse(decodedJwtJsonData);
        this.store.setData(AUTH.token, jwt);
        this.store.setData(AUTH.expiration, decodedJwtData.exp);
        this.store.setData(AUTH.username, decodedJwtData.sub);
        const roles: any[] = decodedJwtData.roles;
        let role = '';
        if (roles.length > 0) {
          roles.forEach(value => {
            role += value.authority.concat(',');
          });
        }
        this.store.setData(AUTH.roles, role);
        this.snackBar.open('login success', '', {
          duration: 1000
        }).afterDismissed().subscribe(() => {
          this.router.navigate(['home']);
        });
      } else {
        this.snackBar.open('Invalid credentials', '', {
          duration: 3000
        });
      }
    });
  }
}
