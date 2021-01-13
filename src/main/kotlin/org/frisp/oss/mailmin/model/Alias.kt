package org.frisp.oss.mailmin.model

import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "aliases")
data class Alias(
        @Id
        @Column(name = "uuid")
        var uuid: UUID,

        @Column(name = "owner")
        var owner: String,

        @Column(name = "source_username")
        var sourceUsername: String,

        @Column(name = "source_domain")
        var sourceDomain: String,

        @Column(name = "destination_username")
        var destinationUsername: String,

        @Column(name = "destination_domain")
        var destinationDomain: String,

        @Column(name = "enabled")
        var enabled: Boolean,

        @Column(name = "accepted")
        var accepted: Boolean
)
