import {User} from "../model/user.model";

export class UserMapper {
    static toModel(dto: any): User {
        return {
            username: dto.username,
            displayName: dto.displayName,
            admin: dto.admin
        }
    }
}
