package project.structure.sample.repository

import project.structure.sample.Sample
import project.structure.support.ActiveRepository

interface SampleRepository : ActiveRepository<Sample, Long> {
    fun save(sample: Sample) : Sample
}
