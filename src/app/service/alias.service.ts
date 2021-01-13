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

    readAll(): Observable<Alias[]> {
        return this.http.get(CommonConstants.ALIASES_RESOURCE).pipe(
            map((data: any) => data.map(dto => AliasMapper.toModel(dto)))
        )
    }
}
