package com.usktea.security.entities

import jakarta.persistence.*
import org.hibernate.internal.util.collections.CollectionHelper.*

@Entity
class AdminMember(
    @Column(length = 200)
    var username: String,

    @Column(length = 10)
    @Enumerated(EnumType.STRING)
    var status: AdminStatus = AdminStatus.ACTIVE,
    var password: String? = null,

    @OneToMany(cascade = [CascadeType.ALL], mappedBy = "adminMember")
    var roles: MutableSet<AdminRole> =  setOf()

) : BaseEntity()

enum class AdminStatus {
    ACTIVE, INACTIVE, LEAVE
}
