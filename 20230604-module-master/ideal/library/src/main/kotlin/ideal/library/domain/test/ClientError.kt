package ideal.library.domain.test

import ideal.core.model.BaseEntity
import jakarta.persistence.*

@Entity
class ClientError(
    var firebaseUid: String,
    var path: String? = null,
    var platform: String? = null,

    @Lob
//    @Type(type = "org.hibernate.type.TextType")
    @Column(columnDefinition = "TEXT")
    var message: String? = null,
    @Lob
//    @Type(type = "org.hibernate.type.TextType")
    @Column(columnDefinition = "TEXT")
    var rawContent: String? = null
) : BaseEntity()
