import {Component, OnInit} from "@angular/core";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {User} from "../model/user.model";
import {UserService} from "../service/user.service";
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";
import {MatDialog} from "@angular/material/dialog";
import {Util} from "../util/util";

@Component({
    selector: 'domains-component',
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

    constructor(private dialog: MatDialog, private formBuilder: FormBuilder, private domainService: DomainService, private userService: UserService) {
    }

    ngOnInit() {
        this.fetchData();

        this.userService.currentUser().subscribe(user => this.user = user);
    }

    submitNewDomain() {
        this.domainService.create(this.newDomainForm.get('domainName').value)
            .subscribe(_ => {
                this.fetchData();
                this.newDomainForm.reset();
            })
    }

    delete(domain: string) {
        this.dialog.open(ConfirmationDialogComponent, {
            data: {
                message: 'Are you sure you want to delete the domain <code>' + Util.escapeHtml(domain) + '</code>?'
            }
        }).afterClosed().subscribe(result => {
            if (result) {
                this.domainService.deleteOne(domain).subscribe(() => this.fetchData());
            }
        });
    }

    refresh() {
        this.newDomainForm.reset();
        this.fetchData();
    }

    private fetchData() {
        this.isLoaded = false;

        this.domainService.readAll().subscribe(domains => {
            this.dataSource.data = domains;
            this.isLoaded = true;
        });
    }
}
