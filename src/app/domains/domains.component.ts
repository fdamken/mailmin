import {Component, OnInit} from "@angular/core";
import {Domain} from "../model/domain.model";
import {DomainService} from "../service/domain.service";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
    selector: 'app-component',
    templateUrl: './domains.component.html',
    styleUrls: ['./domains.component.css']
})
export class DomainsComponent implements OnInit {
    isLoaded: boolean = false;
    domains: Domain[] = [];

    displayedColumns = ['domain', 'actions'];

    // Without the trailing comma it doesn't work.
    // noinspection JSConsecutiveCommasInArrayLiteral
    newDomainForm: FormGroup = this.formBuilder.group({
        domainName: [, {validators: [Validators.required], updateOn: 'change'}]
    })

    constructor(private formBuilder: FormBuilder, private domainService: DomainService) {
    }

    ngOnInit() {
        this.fetchData();
    }

    delete(domain) {
        this.domainService.deleteOne(domain).subscribe(() => this.fetchData());
    }

    submitNewDomain() {
        this.domainService.create(this.newDomainForm.get('domainName').value)
            .subscribe((_) => {
                this.fetchData();
                this.newDomainForm.reset();
            })
    }

    private fetchData() {
        this.isLoaded = false;

        this.domainService.readAll().subscribe(domains => {
            this.domains = domains;

            this.isLoaded = true;
        });
    }
}
