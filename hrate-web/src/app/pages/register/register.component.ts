import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AuthenticationService} from '../../services/authentication.service';
import {EsResponse} from '../model/es-response';
import {RoleService} from '../../services/role.service';
import {ES_RES_STATUS_OK} from '../model/constants';
import {Router} from '@angular/router';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  public registerForm: FormGroup;
  public roles: any[] = [];

  constructor(private authService: AuthenticationService,
              private roleService: RoleService,
              private router: Router,
              private snackBar: MatSnackBar) {
  }

  ngOnInit(): void {
    this.registerForm = new FormGroup({
      name: new FormControl('', [Validators.required]),
      contactNo: new FormControl('', []),
      username: new FormControl('', [Validators.required]),
      password: new FormControl('', [Validators.required]),
      userType: new FormControl('', [Validators.required]),
    });
    this.roleService.findAllRoles().subscribe((res: EsResponse) => {
      if (res.status === ES_RES_STATUS_OK) {
        this.roles = res.data;
      }
    });
  }

  onRegister() {
    const user: any = this.registerForm.value;
    user.roles = [this.registerForm.value.userType];
    this.authService.onRegister(user).subscribe((res: EsResponse) => {
      if (res.status === ES_RES_STATUS_OK) {
        const snackBarRef = this.snackBar.open('user successfully created', 'go to login', {
          duration: 2000
        });
        snackBarRef.afterDismissed().subscribe(() => {
          this.router.navigate(['/']);
        });
        snackBarRef.onAction().subscribe(() => {
          this.router.navigate(['/']);
        });
      } else {
        this.snackBar.open('new user creation failed', '', {
          duration: 3000
        });
      }
    });
  }
}
