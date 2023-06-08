package project.structure.sample.service.impl

import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import project.structure.sample.Sample
import project.structure.sample.repository.SampleRepository
import project.structure.sample.service.SampleService

@Service
class SampleServiceImpl(
    private val sampleRepository: SampleRepository
): SampleService {

    @Transactional
    override fun test(): String {
        val sample = Sample(
            commonMessage = "common",
            adminMessage = "admin",
            appMessage = "app"
        )

        sampleRepository.save(sample)

        val firstError = sampleRepository.findAll()[0]

        return sample.commonMessage!!
    }
}