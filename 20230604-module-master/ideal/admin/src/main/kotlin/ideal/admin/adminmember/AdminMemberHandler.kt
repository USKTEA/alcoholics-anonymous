import mu.KotlinLogging
import org.springframework.stereotype.Component

private val log = KotlinLogging.logger {}

@Component
class AdminMemberHandler(
//    private val adminMemberService: AdminMemberService
) {

//    //    @PreAuthorize("hasRole('SUPER')")
//    suspend fun create(request: ServerRequest): ServerResponse {
//        log.info { "]-----] AdminMemberHandler::create call [-----[ " }
//
//        val word = request.awaitBody<String>()
//        return
//    }
}

//        val adminId = request.awaitPrincipal()!!.name.toLong()


//        val adminMemberReq = request.awaitBody<CreateAdminMemberReq>()
//        log.debug { "]-----] AdminMemberHandler::validate adminMemberReq [-----[ ${adminMemberReq}" }
//        validate(adminMemberReq)
//        return adminMemberService.create(adminMemberReq, adminId).let {
//            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
//        }
//    private val validator = AdminMemberValidator()
//    private fun validate(adminMemberReq: CreateAdminMemberReq) {
//        val errors: Errors = BeanPropertyBindingResult(adminMemberReq, CreateAdminMemberReq::class.java.name)
//        log.debug { "]-----] AdminMemberHandler::validate errors [-----[ ${errors}" }
//        validator.validate(adminMemberReq, errors)
//        if (errors.hasErrors()) {
//            throw ServerWebInputException(errors.toString())
//        }
//    }
//
//    @PreAuthorize("hasRole('SUPER')")
//    suspend fun findAll(request: ServerRequest): ServerResponse {
//        log.info { "]-----] AdminMemberHandler::findAll call [-----[ " }
//        val adminId = request.awaitPrincipal()!!.name.toLong()
//        val sort = Sort.by(Sort.Direction.DESC, "id")
//        val page = if (request.queryParam("page").isPresent) request.queryParam("page").get().toInt() else DEFAULT_PAGE
//        val size = if (request.queryParam("size").isPresent) request.queryParam("size").get().toInt() else DEFAULT_SIZE
//        return adminMemberService.findAll(PageRequest.of(page, size, sort)).let {
//            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
//        }
//    }
//
//    @PreAuthorize("hasRole('SUPER')")
//    suspend fun findById(request: ServerRequest): ServerResponse {
//        val id = request.pathVariable("id").toLong()
//        log.info { "]-----] AdminMemberHandler::findById call [-----[ ${id}" }
//        return adminMemberService.findById(id).let {
//            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
//        }
//    }
//
//    @PreAuthorize("hasRole('SUPER')")
//    suspend fun update(request: ServerRequest): ServerResponse {
//        log.info { "]-----] AdminMemberHandler::update call [-----[ " }
//        val id = request.pathVariable("id").toLong()
//        val adminMemberReq = request.awaitBody<UpdateAdminMemberReq>()
//        val adminId = request.awaitPrincipal()!!.name.toLong()
//
//        log.debug { "]-----] AdminMemberHandler::validate adminMemberReq [-----[ ${adminMemberReq}" }
//        validateUpdate(adminMemberReq)
//        return adminMemberService.update(id, adminMemberReq, adminId).let {
//            ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValueAndAwait(it)
//        }
//    }
//
//
//    private val updateValidator = AdminMemberUpdateValidator()
//    private fun validateUpdate(adminMemberReq: UpdateAdminMemberReq) {
//        val errors: Errors = BeanPropertyBindingResult(adminMemberReq, UpdateAdminMemberReq::class.java.name)
//        updateValidator.validate(adminMemberReq, errors)
//        if (errors.hasErrors()) {
//            throw ServerWebInputException(errors.toString())
//        }
//    }
//}
//
//
//class AdminMemberValidator : Validator {
//    private val usernameRegex = Regex("^(?=.*[a-z])[a-z0-9()]{8,25}$")
//    private val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^()])[a-zA-Z0-9!@#$%^()]{8,25}$")
//
//    /**
//     * This Validator validates only CreateAdminMemberReq instances
//     */
//    override fun supports(clazz: Class<*>): Boolean {
//        return CreateAdminMemberReq::class.java == clazz
//    }
//
//    override fun validate(obj: Any, error: Errors) {
//        ValidationUtils.rejectIfEmpty(error, "username", "username.empty")
//        ValidationUtils.rejectIfEmpty(error, "password", "password.empty")
//        val adminMemberReq = obj as CreateAdminMemberReq
//        if (!usernameRegex.matches(adminMemberReq.username)) {
//            error.rejectValue("username", "negativevalue")
//        }
//        try {
//            val password = String(Base64.getDecoder().decode(adminMemberReq.password))
//            if (!passwordRegex.matches(password)) {
//                error.rejectValue("password", "negativevalue")
//            }
//        } catch (e: Exception) {
//            log.error { "]-----] AdminMemberValidator::validate exception [-----[ ${e}" }
//        }
//    }
//}
//
//class AdminMemberUpdateValidator : Validator {
//    private val usernameRegex = Regex("^(?=.*[a-z])[a-z0-9()]{8,25}$")
//    private val passwordRegex = Regex("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^()])[a-zA-Z0-9!@#$%^()]{8,25}$")
//
//    /**
//     * This Validator validates only CreateAdminMemberReq instances
//     */
//    override fun supports(clazz: Class<*>): Boolean {
//        return UpdateAdminMemberReq::class.java == clazz
//    }
//
//    override fun validate(obj: Any, error: Errors) {
//        val adminMemberReq = obj as UpdateAdminMemberReq
//        if (adminMemberReq.username != null) {
//            if (!usernameRegex.matches(adminMemberReq.username)) {
//                error.rejectValue("username", "negativevalue")
//            }
//        }
//        if (adminMemberReq.password != null) {
//            try {
//                val password = String(Base64.getDecoder().decode(adminMemberReq.password))
//                if (!passwordRegex.matches(password)) {
//                    error.rejectValue("password", "negativevalue")
//                }
//            } catch (e: Exception) {
//                log.error { "]-----] AdminMemberValidator::validate exception [-----[ ${e}" }
//            }
//        }
//    }
//}