package project.structure.sample.service.`interface`

import project.structure.sample.view.SampleMessage

interface CreateSampleService {
    fun createSample(key: String): SampleMessage
    fun createSample(): SampleMessage
}