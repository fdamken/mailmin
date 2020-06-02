import {Observable} from "rxjs";
import {Domain} from "../model/domain.model";
import {HttpClient} from "@angular/common/http";
import {map} from "rxjs/operators";
import {DomainMapper} from "../mapper/domain.mapper";
import {Injectable} from "@angular/core";

@Injectable()
export class DomainService {
    constructor(private http: HttpClient) {
    }

    readAll(): Observable<Domain[]> {
        return this.http.get('api/v1/domains').pipe(
            map((data: any) => data.map(dto => DomainMapper.toModel(dto)))
        )
    }

    deleteOne(domain: Domain): Observable<any> {
        return this.http.delete('api/v1/domains/' + encodeURIComponent(domain.domain));
    }
}
