import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {AccountService} from "../service/account.service";
import {Account} from "../model/account.model";
import {User} from "../model/user.model";
import {UserService} from "../service/user.service";
import {LdapUser} from "../model/ldap-user.model";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";

@Component({
    selector: 'app-component',
    templateUrl: './accounts.component.html',
    styleUrls: ['./accounts.component.css']
})
export class AccountsComponent implements OnInit, AfterViewInit {
    isLoaded = false;
    dataSource = new MatTableDataSource<Account>()

    @ViewChild(MatSort)
    sort: MatSort;

    displayedColumns = ['username', 'domain', 'quota', 'sendonly', 'enabled', 'accepted', 'actions'];

    user: User;
    users: LdapUser[];
    domains: Domain[];
    formDataLoaded = false;

    // Without the trailing comma it doesn't work.
    // noinspection JSConsecutiveCommasInArrayLiteral
    newAccountForm: FormGroup = this.formBuilder.group({
        username: [, {validators: [Validators.required], updateOn: 'change'}],
        domain: [, {validators: [Validators.required], updateOn: 'change'}],
        quota: [, {validators: [Validators.min(0)], updateOn: 'change'}],
        sendonly: [,]
    });

    constructor(private formBuilder: FormBuilder, private accountService: AccountService, private domainService: DomainService, private userService: UserService) {
    }

    ngOnInit() {
        this.fetchData();
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
    }

    submitNewAccount() {
        const username = this.newAccountForm.get('username').value;
        const domain = this.newAccountForm.get('domain').value;
        const quota = this.newAccountForm.get('quota').value || 0;
        const sendonly = this.newAccountForm.get('sendonly').value;
        this.accountService.create(username, domain, quota, sendonly).subscribe(_ => {
            this.fetchData();
            this.newAccountForm.reset();
        });
    }

    setSendonly(uuid: string, sendonly: boolean) {
        this.accountService.setSendonly(uuid, sendonly).subscribe(_ => this.fetchData());
    }

    setEnabled(uuid: string, enabled: boolean) {
        this.accountService.setEnabled(uuid, enabled).subscribe(_ => this.fetchData());
    }

    setAccepted(uuid: string, accepted: boolean) {
        this.accountService.setAccepted(uuid, accepted).subscribe(_ => this.fetchData());
    }

    delete(uuid: string) {
        this.accountService.delete(uuid).subscribe(() => this.fetchData());
    }

    private fetchData() {
        this.isLoaded = false;
        this.formDataLoaded = false;

        this.accountService.readAll().subscribe(accounts => {
            this.dataSource.data = accounts;
            this.isLoaded = true;
        });

        this.userService.currentUser().subscribe(user => {
            this.user = user;
            this.domainService.readAll().subscribe(domains => {
                this.domains = domains;
                if (this.user.admin) {
                    this.userService.readAll().subscribe(users => {
                        this.users = users;
                        this.formDataLoaded = true;
                    });
                } else {
                    this.newAccountForm.get('username').setValue(this.user.username);
                    this.formDataLoaded = true;
                }
            });
        });
    }
}
