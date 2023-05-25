package com.usktea.security.entities

import jakarta.persistence.*

@Entity
@Table(uniqueConstraints = [UniqueConstraint(columnNames = ["admin_member_id", "admin_role_type"])])
class AdminRole(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_member_id")
    var adminMember: AdminMember,

    @Enumerated(EnumType.STRING)
    @Column(name = "admin_role_type")
    var adminRoleType: AdminRoleType

) : BaseEntity()

enum class AdminRoleType {
    ROLE_ADMIN, ROLE_SUPER
}