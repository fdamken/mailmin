package org.frisp.oss.mailadmin.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.IdClass

@Entity(name = "accounts")
data class Account(
        @Id
        @Column(name = "id")
        var id: Int,

        @Column(name = "username")
        var username: String,

        @Column(name = "domain")
        var domain: String,

        @Column(name = "quota")
        var quota: Int,

        @Column(name = "enabled")
        var enabled: Boolean,

        @Column(name = "sendonly")
        var sendonly: Boolean,

        @Column(name = "accepted")
        var accepted: Boolean
)
