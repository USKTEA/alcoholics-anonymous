package ideal.library.domain.test.service.impl

import ideal.library.domain.test.ClientError
import ideal.library.domain.test.repository.ClientErrorRepository
import ideal.library.domain.test.service.TestService
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TestServiceImpl(
    private val clientErrorRepository: ClientErrorRepository
): TestService {

    @Transactional
    override fun test(word: String): String {
        clientErrorRepository.save(ClientError(
            firebaseUid = "dddd",
            path = "111",
            platform = null,
            message = "입니다",
            rawContent = null
        ))

        val firstError = clientErrorRepository.findAll()[0]

        return firstError.message!!
    }
}