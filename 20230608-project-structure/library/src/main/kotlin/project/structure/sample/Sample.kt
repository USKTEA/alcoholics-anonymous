package project.structure.sample

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Lob
import project.structure.model.BaseEntity

@Entity
class Sample(
    @Lob
//    @Type(type = "org.hibernate.type.TextType")
    @Column(columnDefinition = "TEXT")
    var commonMessage: String? = null,

    @Column(columnDefinition = "TEXT")
    var adminMessage: String? = null,

    @Column(columnDefinition = "TEXT")
    var appMessage: String? = null,

    @Lob
//    @Type(type = "org.hibernate.type.TextType")
    @Column(columnDefinition = "TEXT")
    var rawContent: String? = null
) : BaseEntity()
