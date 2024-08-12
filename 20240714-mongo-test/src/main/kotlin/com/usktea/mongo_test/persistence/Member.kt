package com.usktea.mongo_test.persistence

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonValue
import jakarta.validation.constraints.NotBlank
import java.io.Serializable
import java.time.LocalDate
import java.time.OffsetDateTime
import java.time.Period
import java.time.ZoneId
import java.util.UUID
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document("MEMBER")
class Member(
    @Id
    @field:NotBlank(message = "아이디를 입력하세요")
    override val blueId: String,

    @field:NotBlank(message = "이름을 입력하세요")
    override var name: String,

    @JsonProperty("phone_number")
    override var phone_number: String? = null,

    override var email: String? = null,

    @Deprecated("sex column으로 대체한다.")
    override var gender: Gender,

    @field:NotBlank(message = "생년월일을 입력하세요")
    override var birthdate: String,

    @Indexed(unique = true, sparse = true)
    @field:NotBlank(message = "닉네임을 입력하세요")
    override var nickname: String,
    override var identityVerification: IdentityVerification? = null,

    override var picture: String? = null,
    override val pin_verified: MutableSet<SiteDongHo> = mutableSetOf(),
    override val pin_requested: MutableSet<SiteDongHo> = mutableSetOf(),
    override val createdAt: OffsetDateTime,
    override val agreedTerms: MutableSet<AgreedTerm> = mutableSetOf(),

    override val unKnownMember: Boolean? = false,

    override val platform: Platform = Platform.byb,

    var inactive: Boolean = false,

    var isDormant: Boolean? = null,
    var lastDeactivationNotifiedTime: OffsetDateTime? = null,

    val migrated: Migrated? = null,

    /**
     * version 2 for gender.
     * migrated, unknownMember
     */
    override var sex: Sex = Sex.from(gender, unKnownMember, migrated),

    /**
     * 로그인 시 blueId 대신 사용가능한 id
     */
    @Indexed(unique = true)
    var loginId: String? = null,
    /**
     * 존재하면, managerProfile에 의해 관리되는 회원임을 의미한다.
     */
    val managerProfileId: ObjectId? = null,

    /**
     * 아너힐즈 단지에 제공되었던 회원가입툴을 통해 가입한 회원을 구분하기 위한 flag값
     */
    val manuallyCreated: Boolean = false,
    val pin_rejected: MutableSet<SiteDongHo> = mutableSetOf(),

    /**
     * 소셜 IDP로 생성된 회원을 구분하기 위한 flag
     */
    val externalIdpCreated: Boolean = false,

    favoriteApartment: FavoriteApartment? = null,
    _identities: Set<ExternalIdentity> = setOf(),

    personalVerification: PersonalVerification? = null,
    legalRepresentativeVerification: LegalRepresentativeVerification? = null,

    apartmentToEnterNextLogin: Apartment? = null,
    deviceInfo: DeviceInfo? = null,

    imageProfileSrc: S3FileLocation? = null,
    residentVerifyRequestRecords: MutableSet<ResidentVerifyRequestRecord> = mutableSetOf(),
) : MemberInterface {
    @LastModifiedDate
    override var lastModifiedAt: OffsetDateTime? = null

    var favoriteApartment: FavoriteApartment? = favoriteApartment
        private set

    private val _identities: MutableSet<ExternalIdentity> = _identities.toMutableSet()

    val identities: Set<ExternalIdentity>
        get() = _identities.toSet()

    var personalVerification: PersonalVerification? = personalVerification
        private set

    var legalRepresentativeVerification: LegalRepresentativeVerification? = legalRepresentativeVerification
        private set

    var apartmentToEnterNextLogin: Apartment? = apartmentToEnterNextLogin
        private set

    var deviceInfo: DeviceInfo? = deviceInfo
        private set

    var imageProfileSrc: S3FileLocation? = imageProfileSrc
        private set

    val residentVerifyRequestRecords: MutableSet<ResidentVerifyRequestRecord> = residentVerifyRequestRecords


    companion object {
        private const val CAN_NOT_UPDATE_AFTER_PERSONAL_VERIFIED = "본인인증 후 개인정보는 변경할 수 없습니다."
        private fun checkIfEmptyString(name: String): Boolean {
            return name == ""
        }

        private fun getGenderFromSex(sex: Sex) = when (sex) {
            Sex.MALE -> Gender.male
            Sex.FEMALE -> Gender.female
            /**
             * 1.0 gender는 UNDEFINED를 표현할 수 없어서 임시 처리
             */
            else -> Gender.male
        }
    }
}

@Suppress("EnumEntryName")
enum class Gender {
    male,
    female,
    ;

    companion object {
        fun random(): Gender = entries.random()
    }
}

@Suppress("EnumEntryName")
enum class Platform(
    private val deactivationTemplateId: String,
    private val authenticationTemplateId: String,
    private val deactivationTemplateCode: String,
) {
    byb(
        deactivationTemplateId = "bybNotifyDeactivationTemplate",
        authenticationTemplateId = "bybCodeTemplate",
        deactivationTemplateCode = "bybBizNotify",

        ),
    posco_the_sharp(
        deactivationTemplateId = "poscoNotifyDeactivationTemplate",
        authenticationTemplateId = "poscoCodeTemplate",
        deactivationTemplateCode = "PoscoBizNotify",

        ),
    ;

    fun getDeactivationTemplateId(): String {
        return this.deactivationTemplateId
    }

    fun getAuthenticationTemplateId(): String {
        return this.authenticationTemplateId
    }

    fun getDeactivationTemplateCode(): String {
        return this.deactivationTemplateCode
    }
}

open class SiteDongHo(
    open val dong: String,
    open val ho: String,
    open val siteId: String,
) : Serializable {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SiteDongHo) return false

        if (dong != other.dong) return false
        if (ho != other.ho) return false
        if (siteId != other.siteId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = dong.hashCode()
        result = 31 * result + ho.hashCode()
        result = 31 * result + siteId.hashCode()
        return result
    }

    fun contains(apartment: Apartment): Boolean {
        return this.siteId == apartment.siteId &&
                apartment.dong?.let { this.dong == it } ?: true &&
                apartment.ho?.let { this.ho == it } ?: true
    }
}

class Site(
    val siteId: String,
    val dong: String?,
    val ho: String?,
)

data class Apartment(
    val apartmentId: String,
    val siteId: String?,
    val dong: String?,
    val ho: String?,
)

data class DongHoWithVerification(
    val siteId: String,
    val dong: String,
    val ho: String,
    val verified: Boolean,
) {
    constructor(dongHo: SiteDongHo, verified: Boolean) : this(
        dongHo.siteId,
        dongHo.dong,
        dongHo.ho,
        verified,
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as DongHoWithVerification

        if (siteId != other.siteId) return false
        if (dong != other.dong) return false
        if (ho != other.ho) return false

        return true
    }

    override fun hashCode(): Int {
        var result = siteId.hashCode()
        result = 31 * result + dong.hashCode()
        result = 31 * result + ho.hashCode()
        return result
    }
}

class DeviceInfo(
    val deviceId: String,
    val deviceType: String,
)

data class IdentityVerification(
    val name: String,
    val phone_number: String,
    val gender: Gender,
    val birthdate: String,
    val isForeigner: Boolean,
    val isLegalRepresentative: Boolean = false,
    val ci: String? = null,
    val di: String? = null,
)

class Migrated(
    updated: Boolean = false,
    val migratedTime: OffsetDateTime = OffsetDateTime.now(),
) {
    var updated = updated
        private set

    fun update() {
        updated = true
    }
}

class AgreedTerm(
    val category: String,
    // 이미가입한 회원의 경우 clientId와 version이 null이기때문에 nullable로 처리
    var clientId: String? = null,
    var version: Long? = null,
    var id: UUID? = null,
    val agreedAt: OffsetDateTime = OffsetDateTime.now(),
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as AgreedTerm

        if (category != other.category) return false
        if (clientId != other.clientId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = category.hashCode()
        result = 31 * result + (clientId?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "AgreedTerm(category='$category', clientId=$clientId, version=$version, id=$id, agreedAt=$agreedAt)"
    }
}

enum class Sex {
    MALE,
    FEMALE,

    /**
     * 정해진 성별이 없는 상태
     */
    UNDEFINED,
    ;

    companion object {
        fun from(gender: Gender? = null, isUnknownMember: Boolean? = null, migrated: Migrated? = null): Sex {
            if (migrated?.updated == false) {
                return UNDEFINED
            }
            if (isUnknownMember == true) {
                return UNDEFINED
            }
            return when (gender) {
                Gender.male -> MALE
                Gender.female -> FEMALE
                else -> UNDEFINED
            }
        }
    }
}

class FavoriteApartment(
    val apartmentId: String,
    val siteId: String?,
    val selectedAt: OffsetDateTime,
)

data class ExternalIdentity(
    val providerName: String,
    val userId: String,
    val createdAt: OffsetDateTime,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ExternalIdentity) return false

        if (providerName != other.providerName) return false
        if (userId != other.userId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = providerName.hashCode()
        result = 31 * result + userId.hashCode()
        return result
    }
}


data class PersonalVerification(
    val ci: String?,
    val di: String?,
    val name: String,
    val phoneNumber: String,
    val gender: Gender,
    val birthdate: String,
    val isForeigner: Boolean,
)

interface MemberInterface {
    val blueId: String
    var name: String

    @Suppress("ktlint:standard:property-naming")
    var phone_number: String?
    var email: String?
    var gender: Gender
    var sex: Sex
    var birthdate: String
    var nickname: String
    var picture: String?

    @Suppress("ktlint:standard:property-naming")
    val pin_verified: MutableSet<SiteDongHo>

    @Suppress("ktlint:standard:property-naming")
    val pin_requested: MutableSet<SiteDongHo>
    var identityVerification: IdentityVerification?
    val agreedTerms: MutableSet<AgreedTerm>
    val unKnownMember: Boolean?
    val platform: Platform
    val createdAt: OffsetDateTime
    var lastModifiedAt: OffsetDateTime?
}

const val ZONE_SEOUL = "Asia/Seoul"

data class LegalRepresentativeVerification(
    val ci: String?,
    val di: String?,
    val name: String,
    val phoneNumber: String,
    val gender: Gender,
    val birthdate: String,
    val isForeigner: Boolean,
) {

    init {
        val age = Period.between(LocalDate.parse(birthdate), LocalDate.now(ZoneId.of(ZONE_SEOUL))).years
        require(age > 19)
    }
}

open class CommonS3FileLocation(
    open var key: String,
    open var level: AwsAmplifyS3FileAccessLevel,
    open var identityId: String?,
) {

    enum class AwsAmplifyS3FileAccessLevel(
        @get:JsonValue
        val jsonValue: String,
    ) {
        PRIVATE("private"),
        PUBLIC("public"),
        PROTECTED("protected"),
    }
}


class S3FileLocation(
    var key: String,
    var level: CommonS3FileLocation.AwsAmplifyS3FileAccessLevel,
    var identityId: String?,
) {
    fun toEvent(): S3FileLocationEvent {
        return S3FileLocationEvent(
            key = key,
            level = level.jsonValue,
            identityId = identityId,
        )
    }

    fun toCommonS3FileLocation(): CommonS3FileLocation {
        return CommonS3FileLocation(
            key = this.key,
            level = this.level,
            identityId = this.identityId,
        )
    }

    companion object {
        private val defaultProfileImageKeys = listOf(
            "static/icon_placeholder_001.png",
            "static/icon_placeholder_002.png",
            "static/icon_placeholder_003.png",
            "static/icon_placeholder_004.png",
        )

        private val defaultManagerProfileImageKeys = listOf(
            "static/icon_manager_placeholder_001.png",
            "static/icon_manager_placeholder_002.png",
            "static/icon_manager_placeholder_003.png",
            "static/icon_manager_placeholder_004.png",
        )

        fun randomFromDefault() = S3FileLocation(
            key = defaultProfileImageKeys.random(),
            level = CommonS3FileLocation.AwsAmplifyS3FileAccessLevel.PUBLIC,
            identityId = null,
        )

        fun randomFromManagerDefault() = S3FileLocation(
            key = defaultManagerProfileImageKeys.random(),
            level = CommonS3FileLocation.AwsAmplifyS3FileAccessLevel.PUBLIC,
            identityId = null,
        )
    }
}

data class S3FileLocationEvent(
    val key: String,
    val level: String,
    val identityId: String?,
)

fun S3FileLocation.toCommonS3FileLocation(): CommonS3FileLocation {
    return CommonS3FileLocation(
        key = key,
        level = level,
        identityId = identityId,
    )
}

class ResidentVerifyRequestRecord(
    val siteDongHo: SiteDongHo,
    val requestedAt: OffsetDateTime,
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ResidentVerifyRequestRecord) return false

        if (siteDongHo != other.siteDongHo) return false

        return true
    }

    override fun hashCode(): Int {
        return siteDongHo.hashCode()
    }
}
