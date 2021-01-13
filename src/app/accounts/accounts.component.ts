import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";
import {AccountService} from "../service/account.service";
import {Account} from "../model/account.model";

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

    displayedColumns = ['username', 'domain', 'quota', 'sendonly', 'enabled', 'accepted'];

    constructor(private formBuilder: FormBuilder, private accountService: AccountService) {
    }

    ngOnInit() {
        this.fetchData();
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
    }

    private fetchData() {
        this.isLoaded = false;

        this.accountService.readAll().subscribe(aliases => {
            this.dataSource.data = aliases;
            this.isLoaded = true;
        });
    }
}
