import {AfterViewInit, Component, OnInit, ViewChild} from "@angular/core";
import {FormBuilder} from "@angular/forms";
import {Alias} from "../model/alias.model";
import {AliasService} from "../service/alias.service";
import {MatTableDataSource} from "@angular/material/table";
import {MatSort} from "@angular/material/sort";

@Component({
    selector: 'app-component',
    templateUrl: './aliases.component.html',
    styleUrls: ['./aliases.component.css']
})
export class AliasesComponent implements OnInit, AfterViewInit {
    isLoaded: boolean = false;
    dataSource = new MatTableDataSource<Alias>()

    @ViewChild(MatSort)
    sort: MatSort;

    displayedColumns = ['id', 'owner', 'sourceUsername', 'sourceDomain', 'destinationUsername', 'destinationDomain', 'enabled', 'accepted'];

    constructor(private formBuilder: FormBuilder, private aliasService: AliasService) {
    }

    ngOnInit() {
        this.fetchData();
    }

    ngAfterViewInit() {
        this.dataSource.sort = this.sort;
    }

    private fetchData() {
        this.isLoaded = false;

        this.aliasService.readAll().subscribe(aliases => {
            this.dataSource.data = aliases;

            this.isLoaded = true;
        });
    }
}
