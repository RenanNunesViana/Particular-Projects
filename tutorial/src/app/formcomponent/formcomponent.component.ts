import { Component } from '@angular/core';
import { FormControl } from '@angular/forms'
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../user';
import { UserServiceService } from '../user-service.service';

@Component({
  selector: 'app-formcomponent',
  templateUrl: './formcomponent.component.html',
  styleUrls: ['./formcomponent.component.css']
})
export class FormcomponentComponent {
  user: User;
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserServiceService
  ) {
    this.user = new User;
  }

  onSubmit() {
    this.userService.save(this.user).subscribe(result => this.gotoUserList());
  }

  gotoUserList() {
    this.router.navigate(['/users']);
  }
}
