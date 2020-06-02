import {Domain} from "../model/domain.model";

export class DomainMapper {
    static toModel(dto: any): Domain {
        return {
            domain: dto.domain
        }
    }
}