package org.frisp.oss.mailmin.database.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "accounts")
data class Account(
        @Id
        @Column(name = "uuid")
        var uuid: UUID,

        @Column(name = "username")
        var username: String,

        @Column(name = "domain")
        var domain: String,

        @Column(name = "quota")
        var quota: Int,

        @Column(name = "sendonly")
        var sendonly: Boolean,

        @Column(name = "enabled")
        var enabled: Boolean,

        @Column(name = "accepted")
        var accepted: Boolean
)
