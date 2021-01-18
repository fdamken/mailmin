package org.frisp.oss.mailmin.ldap.model

import org.springframework.ldap.odm.annotations.Attribute
import org.springframework.ldap.odm.annotations.Entry
import org.springframework.ldap.odm.annotations.Id
import javax.naming.Name

@Suppress("unused", "JoinDeclarationAndAssignment")
@Entry(base = "ou=user", objectClasses = ["inetOrgPerson", "top"])
class LdapUser {
    @Id
    lateinit var dn: Name

    @Attribute(name = "uid")
    lateinit var uid: String

    @Attribute(name = "mail")
    lateinit var mail: String

    constructor()

    constructor(dn: Name, uid: String, mail: String) {
        this.dn = dn
        this.uid = uid
        this.mail = mail
    }
}
