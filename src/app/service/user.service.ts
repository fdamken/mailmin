import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {CommonConstants} from "../util/common-constants";
import {User} from "../model/user.model";
import {UserMapper} from "../mapper/user.mapper";
import {LdapUser} from "../model/ldap-user.model";
import {LdapUserMapper} from "../mapper/ldap-user.mapper";

@Injectable()
export class UserService {
    constructor(private http: HttpClient) {
    }

    currentUser(): Observable<User> {
        return this.http.get(CommonConstants.USERS_RESOURCE + '/current').pipe(
            map(dto => UserMapper.toModel(dto))
        )
    }

    readAll(): Observable<LdapUser[]> {
        return this.http.get(CommonConstants.USERS_RESOURCE).pipe(
            map((data: any) => data.map(dto => LdapUserMapper.toModel(dto))),
            map((data: LdapUser[]) => data.sort((a, b) => a.uid.localeCompare(b.uid)))
        )
    }
}
