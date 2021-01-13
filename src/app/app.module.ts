import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {MatButtonModule} from "@angular/material/button";
import {MatToolbarModule} from "@angular/material/toolbar";
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatListModule} from "@angular/material/list";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {MatIconModule} from "@angular/material/icon";
import {DomainsComponent} from "./domains/domains.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {MatProgressSpinnerModule} from "@angular/material/progress-spinner";
import {MatTableModule} from "@angular/material/table";
import {DomainService} from "./service/domain.service";
import {HttpClientModule} from "@angular/common/http";
import {MatTooltipModule} from "@angular/material/tooltip";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {MatInputModule} from "@angular/material/input";
import {AliasesComponent} from "./aliases/aliases.component";
import {AliasService} from "./service/alias.service";
import {MatSortModule} from "@angular/material/sort";
import {CommonModule} from "@angular/common";
import {AccountsComponent} from "./accounts/accounts.component";
import {AccountService} from "./service/account.service";
import {MatSelectModule} from "@angular/material/select";
import {MatCheckboxModule} from "@angular/material/checkbox";
import {UserService} from "./service/user.service";

@NgModule({
    declarations: [
        AppComponent,
        DomainsComponent,
        AliasesComponent,
        DashboardComponent,
        AccountsComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        MatButtonModule,
        MatToolbarModule,
        MatSidenavModule,
        MatListModule,
        CommonModule,
        HttpClientModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatProgressSpinnerModule,
        MatTableModule,
        MatTooltipModule,
        FormsModule,
        MatInputModule,
        ReactiveFormsModule,
        MatSortModule,
        MatSelectModule,
        MatCheckboxModule
    ],
    exports: [MatSortModule],
    providers: [AccountService, AliasService, DomainService, UserService],
    bootstrap: [AppComponent]
})
export class AppModule {
}
