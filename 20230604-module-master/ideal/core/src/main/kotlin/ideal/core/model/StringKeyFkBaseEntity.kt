package ideal.core.model

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.Instant

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class StringKeyFkBaseEntity {

    var createdId: Long? = null
    var updatedId: Long? = null

    @CreatedDate
    lateinit var createdAt: Instant

    @LastModifiedDate
    lateinit var updatedAt: Instant

    @Column(columnDefinition = "boolean default true")
    var active: Boolean = true

}