import {LdapUser} from "../model/ldap-user.model";

export class LdapUserMapper {
    static toModel(dto: any): LdapUser {
        return {
            dn: dto.dn,
            uid: dto.uid,
            mail: dto.mail
        }
    }
}
