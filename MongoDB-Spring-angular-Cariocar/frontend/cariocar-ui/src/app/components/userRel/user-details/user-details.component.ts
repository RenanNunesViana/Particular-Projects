import {Component, OnInit} from '@angular/core';
import {UserService} from "../../../services/user/user.service";
import {ActivatedRoute} from "@angular/router";
import {User} from "../../../models/user";

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit{
  userId!: string | null
  user!:User

  constructor(private userService: UserService, private route:ActivatedRoute) {
  }
  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('userId')
    this.getUser(String(this.userId))
  }

  getUser(id:string){

    this.userService.findUser(BigInt(id)).subscribe(data => this.user = data);
  }

}
