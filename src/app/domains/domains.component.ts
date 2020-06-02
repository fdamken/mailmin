import {Component, OnInit} from "@angular/core";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";

@Component({
    selector: 'app-component',
    templateUrl: './domains.component.html',
    styleUrls: ['./domains.component.css']
})
export class DomainsComponent implements OnInit {
    isLoaded: boolean = false;
    domains: Domain[] = [];

    displayedColumns = ['domain', 'actions'];

    constructor(private domainService: DomainService) {
    }

    ngOnInit() {
        this.fetchData();
    }

    delete(domain) {
        this.domainService.deleteOne(domain).subscribe(() => this.fetchData());
    }

    private fetchData() {
        this.isLoaded = false;

        this.domainService.readAll().subscribe(domains => {
            this.domains = domains;

            this.isLoaded = true;
        });
    }
}
