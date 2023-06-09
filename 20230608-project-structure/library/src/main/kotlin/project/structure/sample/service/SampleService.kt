package project.structure.sample.service

import jakarta.transaction.Transactional
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import project.structure.sample.Sample
import project.structure.sample.property.SampleProperties
import project.structure.sample.repository.SampleRepository
import project.structure.sample.service.`interface`.CreateSampleService
import project.structure.sample.service.`interface`.GetSampleService
import project.structure.sample.view.SampleMessage
import java.util.Objects

@Service
class SampleService(
    private val sampleRepository: SampleRepository,
    private val sampleProperties: SampleProperties? = null
) : GetSampleService, CreateSampleService {
    @Value("\${sampleKey}")
    lateinit var key: String

    @Transactional
    override fun getSample(): String {
        val sample = Sample(
            commonMessage = "common",
            adminMessage = "admin",
            appMessage = "app"
        )

        sampleRepository.save(sample)

        val founded = sampleRepository.findAll()[0]

        return sample.commonMessage!!
    }


    @Transactional
    override fun createSample(): SampleMessage {
        if (Objects.isNull(sampleProperties)) {
            println("It's Admin")

            return SampleMessage(
                common = key,
                app = null,
                admin = null
            )
         }

        println(sampleProperties?.key)

        return SampleMessage(
            common = key,
            app = sampleProperties?.key,
            admin = null
        )
    }
}