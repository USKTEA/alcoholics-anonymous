package ideal.core.view

data class CommonHeader(
    var platform: String, //기존은 Platform클래스 TODO 순환참조 제거할 것
    var platformVersion: String?,
    var appVersion: String?,
    var appBuildNumber: String?,
    var deviceUniqueId: String?,
    var ipAddress: String?
)