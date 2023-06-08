package project.structure.model

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import org.springframework.data.util.ProxyUtils
import java.io.Serializable

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class MappingBaseEntity<T : Serializable> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: T? = null

    override fun equals(other: Any?): Boolean {
        other ?: return false
        if (this === other) return true
        if (javaClass != ProxyUtils.getUserClass(other)) return false
        other as MappingBaseEntity<*>
        return this.id != null && this.id == other.id
    }

    override fun hashCode(): Int {
        val prime = 59
        val result = 1
        return result * prime + (id?.hashCode() ?: 43)
    }

    override fun toString(): String {
        return "${this.javaClass.simpleName}(id=$id)"
    }
}
