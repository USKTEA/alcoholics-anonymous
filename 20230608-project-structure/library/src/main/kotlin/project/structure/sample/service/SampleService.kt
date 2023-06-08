package project.structure.sample.service

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import project.structure.sample.Sample
import project.structure.sample.renderMessage
import project.structure.sample.repository.SampleRepository
import project.structure.sample.service.`interface`.CreateSampleService
import project.structure.sample.service.`interface`.GetSampleService
import project.structure.sample.view.SampleMessage

@Service
class SampleService(
    private val sampleRepository: SampleRepository
) : GetSampleService, CreateSampleService {

    @Transactional
    override fun getSample(): String {
        return Sample(
            commonMessage = "testCommon"
        ).commonMessage!!
    }

    @Transactional
    override fun createSample(key: String): SampleMessage {
        val created = Sample(
            commonMessage = "COMMON",
            appMessage = key
        )

        return sampleRepository.save(created).let {
            it.renderMessage()
        }
    }

    @Transactional
    override fun createSample(): SampleMessage {
        val created = Sample(
            commonMessage = "COMMON",
            adminMessage = "ADMIN"
        )

        return sampleRepository.save(created).let {
            it.renderMessage()
        }
    }
}
