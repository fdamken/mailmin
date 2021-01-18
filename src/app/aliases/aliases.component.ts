import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {AbstractControl, FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Alias} from "../model/alias.model";
import {AliasService} from "../service/alias.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";
import {UserService} from "../service/user.service";
import {User} from "../model/user.model";
import {ConfirmationDialogComponent} from "../confirmation-dialog/confirmation-dialog.component";
import {Util} from "../util/util";
import {MatDialog} from "@angular/material/dialog";

@Component({
    selector: 'aliases-component',
    templateUrl: './aliases.component.html',
    styleUrls: ['./aliases.component.css']
})
export class AliasesComponent implements OnInit, AfterViewInit {
    isLoaded = false;
    dataSource = new MatTableDataSource<Alias>();

    domainsLoaded = false;
    domains: Domain[];

    @ViewChild(MatSort)
    sort: MatSort;

    displayedColumns = ['owner', 'sourceUsername', 'sourceDomain', 'destinationUsername', 'destinationDomain', 'enabled', 'accepted', 'actions'];

    user: User;

    // Without the trailing comma it doesn't work.
    // noinspection JSConsecutiveCommasInArrayLiteral
    newAliasForm: FormGroup = this.formBuilder.group({
        sourceUsername: [, {validators: [Validators.required], updateOn: 'change'}],
        sourceDomain: [, {validators: [Validators.required], updateOn: 'change'}],
        destUsername: [, {validators: [Validators.required], updateOn: 'change'}],
        destDomain: [, {validators: [Validators.required], updateOn: 'change'}]
    });

    constructor(private formBuilder: FormBuilder, private dialog: MatDialog, private aliasService: AliasService, private domainService: DomainService, private userService: UserService) {
        this.newAliasForm.setValidators(this.validateCollision.bind(this));
    }

    ngOnInit() {
        this.fetchData();

        this.userService.currentUser().subscribe(user => this.user = user);
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
    }

    validateCollision(control: AbstractControl): { [key: string]: any } | null {
        let sourceUsername = control.get('sourceUsername').value;
        let sourceDomain = control.get('sourceDomain').value;
        let destUsername = control.get('destUsername').value;
        let destDomain = control.get('destDomain').value;
        if (!sourceUsername || !sourceDomain || !destUsername || !destDomain) {
            return null;
        }
        sourceUsername = sourceUsername.trim().toLowerCase();
        sourceDomain = sourceDomain.trim().toLowerCase();
        destUsername = destUsername.trim().toLowerCase();
        destDomain = destDomain.trim().toLowerCase();
        for (let alias of this.dataSource.data) {
            if (alias.sourceUsername == sourceUsername && alias.sourceDomain == sourceDomain && alias.destinationUsername == destUsername && alias.destinationDomain == destDomain) {
                return {'collision': true};
            }
        }
        return null;
    }

    submitNewAlias() {
        const sourceUsername = this.newAliasForm.get('sourceUsername').value;
        const sourceDomain = this.newAliasForm.get('sourceDomain').value;
        const destUsername = this.newAliasForm.get('destUsername').value;
        const destDomain = this.newAliasForm.get('destDomain').value;
        this.aliasService.create(sourceUsername, sourceDomain, destUsername, destDomain).subscribe(_ => {
            this.fetchData();
            this.newAliasForm.reset();
        });
    }

    setEnabled(uuid: string, enabled: boolean) {
        this.aliasService.setEnabled(uuid, enabled).subscribe(_ => this.fetchData());
    }

    setAccepted(uuid: string, accepted: boolean) {
        this.aliasService.setAccepted(uuid, accepted).subscribe(_ => this.fetchData());
    }

    delete(alias: Alias) {
        const sourceAddress = Util.escapeHtml(alias.sourceUsername) + '@' + Util.escapeHtml(alias.sourceDomain);
        const destAddress = Util.escapeHtml(alias.destinationUsername) + '@' + Util.escapeHtml(alias.destinationDomain);
        this.dialog.open(ConfirmationDialogComponent, {
            data: {
                message: 'Are you sure you want to delete the alias from <code>' + sourceAddress + '</code> to <code>' + destAddress + '</code>?'
            }
        }).afterClosed().subscribe(result => {
            if (result) {
                this.aliasService.delete(alias.uuid).subscribe(_ => this.fetchData());
            }
        });
    }

    refresh() {
        this.newAliasForm.reset();
        this.fetchData();
    }

    private fetchData() {
        this.isLoaded = false;
        this.domainsLoaded = false;

        this.aliasService.readAll().subscribe(aliases => {
            this.dataSource.data = aliases;
            this.isLoaded = true;
        });
        this.domainService.readAll().subscribe(domains => {
            this.domains = domains;
            this.domainsLoaded = true;
        });
    }
}
