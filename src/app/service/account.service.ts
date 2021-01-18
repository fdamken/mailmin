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

    create(username: string, domain: string, quota: number, sendonly: boolean): Observable<Account> {
        return this.http.put(CommonConstants.ACCOUNTS_RESOURCE, {
            'username': username,
            'domain': domain,
            'quota': quota,
            'sendonly': sendonly
        }).pipe(
            map(dto => AccountMapper.toModel(dto))
        );
    }

    readAll(): Observable<Account[]> {
        return this.http.get(CommonConstants.ACCOUNTS_RESOURCE).pipe(
            map((data: any) => data.map(dto => AccountMapper.toModel(dto)))
        );
    }

    setSendonly(uuid: string, sendonly: boolean): Observable<Account> {
        let request;
        if (sendonly) {
            request = this.http.post(CommonConstants.ACCOUNTS_RESOURCE + '/' + encodeURIComponent(uuid) + '/sendonly', null);
        } else {
            request = this.http.delete(CommonConstants.ACCOUNTS_RESOURCE + '/' + encodeURIComponent(uuid) + '/sendonly');
        }
        return request.pipe(map(dto => AccountMapper.toModel(dto)));
    }

    setEnabled(uuid: string, enabled: boolean): Observable<Account> {
        let request;
        if (enabled) {
            request = this.http.post(CommonConstants.ACCOUNTS_RESOURCE + '/' + encodeURIComponent(uuid) + '/enabled', null);
        } else {
            request = this.http.delete(CommonConstants.ACCOUNTS_RESOURCE + '/' + encodeURIComponent(uuid) + '/enabled');
        }
        return request.pipe(map(dto => AccountMapper.toModel(dto)));
    }

    setAccepted(uuid: string, enabled: boolean): Observable<Account> {
        let request;
        if (enabled) {
            request = this.http.post(CommonConstants.ACCOUNTS_RESOURCE + '/' + encodeURIComponent(uuid) + '/accepted', null);
        } else {
            request = this.http.delete(CommonConstants.ACCOUNTS_RESOURCE + '/' + encodeURIComponent(uuid) + '/accepted');
        }
        return request.pipe(map(dto => AccountMapper.toModel(dto)));
    }
}
