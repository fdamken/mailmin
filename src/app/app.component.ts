import {Component, OnInit} from '@angular/core';
import {User} from "./model/user.model";
import {UserService} from "./service/user.service";

@Component({
    selector: 'app-component',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    user: User;

    constructor(private userService: UserService) {
    }

    ngOnInit() {
        this.userService.currentUser().subscribe(user => this.user = user);
    }
}
