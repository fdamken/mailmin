import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Alias} from "../model/alias.model";
import {AliasService} from "../service/alias.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";
import {UserService} from "../service/user.service";
import {User} from "../model/user.model";

@Component({
    selector: 'app-component',
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

    constructor(private formBuilder: FormBuilder, private aliasService: AliasService, private domainService: DomainService, private userService: UserService) {
    }

    ngOnInit() {
        this.fetchData();

        this.userService.currentUser().subscribe(user => this.user = user);
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
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

    delete(uuid: string) {
        this.aliasService.delete(uuid).subscribe(_ => this.fetchData());
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
