package org.frisp.oss.mailmin.database.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "domains")
data class Domain(
        @Id
        @Column(name = "domain")
        var domain: String
)
