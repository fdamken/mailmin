import {Component, OnInit} from "@angular/core";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
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
        domain: [, {validators: [Validators.required], updateOn: 'change'}]
    });

    user: User;

    constructor(private dialog: MatDialog, private formBuilder: FormBuilder, private domainService: DomainService, private userService: UserService) {
        this.newDomainForm.setValidators(this.validateCollision.bind(this));
    }

    ngOnInit() {
        this.fetchData();
    }

    validateCollision(control: AbstractControl): { [key: string]: any } | null {
        let domainName = control.get('domain').value;
        if (!domainName) {
            return null;
        }
        domainName = domainName.trim().toLowerCase();
        for (let domain of this.dataSource.data) {
            if (domain.domain == domainName) {
                return {'collision': true};
            }
        }
        return null;
    }

    submitNewDomain() {
        this.domainService.create(this.newDomainForm.get('domain').value)
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

        this.userService.currentUser().subscribe(user => this.user = user);
    }
}
