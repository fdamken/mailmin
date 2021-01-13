import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {DomainsComponent} from "./domains/domains.component";
import {DashboardComponent} from "./dashboard/dashboard.component";
import {AliasesComponent} from "./aliases/aliases.component";

const routes: Routes = [
    {path: '', redirectTo: 'ng/dashboard', pathMatch: 'full'},
    {path: 'ng/dashboard', component: DashboardComponent},
    {path: 'ng/aliases', component: AliasesComponent},
    {path: 'ng/domains', component: DomainsComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
