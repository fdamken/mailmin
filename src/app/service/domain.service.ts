import {Observable} from "rxjs";
import {Domain} from "../model/domain.model";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {DomainMapper} from "../mapper/domain.mapper";
import {Injectable} from "@angular/core";
import {CommonConstants} from "../util/common-constants";

@Injectable()
export class DomainService {
    constructor(private http: HttpClient) {
    }

    create(domain: string): Observable<Domain> {
        return this.http.put(CommonConstants.DOMAINS_RESOURCE, {'domain': domain}).pipe(
            map((dto: any) => DomainMapper.toModel(dto))
        );
    }

    readAll(): Observable<Domain[]> {
        return this.http.get(CommonConstants.DOMAINS_RESOURCE).pipe(
            map((data: any) => data.map(dto => DomainMapper.toModel(dto)))
        );
    }

    deleteOne(domain: string): Observable<any> {
        return this.http.delete(CommonConstants.DOMAINS_RESOURCE + '/' + encodeURIComponent(domain));
    }
}
