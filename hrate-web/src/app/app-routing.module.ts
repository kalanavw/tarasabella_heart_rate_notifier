import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {DashboardComponent} from './pages/dashboard/dashboard.component';
import {LoginComponent} from './pages/login/login.component';
import {RegisterComponent} from './pages/register/register.component';
import {NavigationComponent} from './pages/navigation/navigation.component';
import {AuthGuard} from './config/auth-gaurd';


const routes: Routes = [
  {path: '', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {
    path: 'home', component: NavigationComponent, canActivate: [AuthGuard],
    children: [
      {path: '', component: DashboardComponent}
    ]
  },
  {path: '**', redirectTo: 'redirect'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
