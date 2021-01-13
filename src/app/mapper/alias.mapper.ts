import {Alias} from "../model/alias.model";

export class AliasMapper {
    static toModel(dto: any): Alias {
        return {
            id: dto.id,
            owner: dto.owner,
            sourceUsername: dto.sourceUsername,
            sourceDomain: dto.sourceDomain.domain,
            destinationUsername: dto.destinationUsername,
            destinationDomain: dto.destinationDomain,
            enabled: dto.enabled,
            accepted: dto.accepted
        }
    }
}
