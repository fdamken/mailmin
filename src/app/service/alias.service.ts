import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {Injectable} from "@angular/core";
import {CommonConstants} from "../util/common-constants";
import {AliasMapper} from "../mapper/alias.mapper";
import {Alias} from "../model/alias.model";

@Injectable()
export class AliasService {
    constructor(private http: HttpClient) {
    }

    create(sourceUsername, sourceDomain, destUsername, destDomain): Observable<Alias> {
        return this.http.put(CommonConstants.ALIASES_RESOURCE, {
            sourceUsername: sourceUsername,
            sourceDomain: sourceDomain,
            destinationUsername: destUsername,
            destinationDomain: destDomain
        }).pipe(map(dto => AliasMapper.toModel(dto)))
    }

    readAll(): Observable<Alias[]> {
        return this.http.get(CommonConstants.ALIASES_RESOURCE).pipe(
            map((data: any) => data.map(dto => AliasMapper.toModel(dto)))
        )
    }

    setEnabled(uuid: string, enabled: boolean) {
        let request;
        if (enabled) {
            request = this.http.post(CommonConstants.ALIASES_RESOURCE + '/' + encodeURIComponent(uuid) + '/enabled', null)
        } else {
            request = this.http.delete(CommonConstants.ALIASES_RESOURCE + '/' + encodeURIComponent(uuid) + '/enabled')
        }
        return request.pipe(map(dto => AliasMapper.toModel(dto)))
    }

    setAccepted(uuid: string, enabled: boolean) {
        let request;
        if (enabled) {
            request = this.http.post(CommonConstants.ALIASES_RESOURCE + '/' + encodeURIComponent(uuid) + '/accepted', null)
        } else {
            request = this.http.delete(CommonConstants.ALIASES_RESOURCE + '/' + encodeURIComponent(uuid) + '/accepted')
        }
        return request.pipe(map(dto => AliasMapper.toModel(dto)))
    }
}
