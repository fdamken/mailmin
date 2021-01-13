import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {CommonConstants} from "../util/common-constants";
import {Account} from "../model/account.model";
import {AccountMapper} from "../mapper/account.mapper";

@Injectable()
export class AccountService {
    constructor(private http: HttpClient) {
    }

    readAll(): Observable<Account[]> {
        return this.http.get(CommonConstants.ACCOUNTS_RESOURCE).pipe(
            map((data: any) => data.map(dto => AccountMapper.toModel(dto)))
        )
    }
}
