package com.inheritence.demo.entity

import jakarta.persistence.Entity

@Entity
class Manager(
    memberName: String
) : Member(memberName = memberName)
