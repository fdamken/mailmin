import {Component, OnInit} from "@angular/core";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {User} from "../model/user.model";
import {UserService} from "../service/user.service";

@Component({
    selector: 'app-component',
    templateUrl: './domains.component.html',
    styleUrls: ['./domains.component.css']
})
export class DomainsComponent implements OnInit {
    isLoaded = false;
    dataSource = new MatTableDataSource<Domain>()

    displayedColumns = ['domain', 'actions'];

    // Without the trailing comma it doesn't work.
    // noinspection JSConsecutiveCommasInArrayLiteral
    newDomainForm: FormGroup = this.formBuilder.group({
        domainName: [, {validators: [Validators.required], updateOn: 'change'}]
    });

    user: User;

    constructor(private formBuilder: FormBuilder, private domainService: DomainService, private userService: UserService) {
    }

    ngOnInit() {
        this.fetchData();

        this.userService.currentUser().subscribe(user => this.user = user);
    }

    delete(domain: string) {
        this.domainService.deleteOne(domain).subscribe(() => this.fetchData());
    }

    submitNewDomain() {
        this.domainService.create(this.newDomainForm.get('domainName').value)
            .subscribe(_ => {
                this.fetchData();
                this.newDomainForm.reset();
            })
    }

    private fetchData() {
        this.isLoaded = false;

        this.domainService.readAll().subscribe(domains => {
            this.dataSource.data = domains;
            this.isLoaded = true;
        });
    }
}
