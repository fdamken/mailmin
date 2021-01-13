import {Account} from "../model/account.model";

export class AccountMapper {
    static toModel(dto: any): Account {
        return {
            id: dto.id,
            username: dto.username,
            domain: dto.domain.domain,
            quota: dto.quota,
            sendonly: dto.sendonly,
            enabled: dto.enabled,
            accepted: dto.accepted
        }
    }
}
