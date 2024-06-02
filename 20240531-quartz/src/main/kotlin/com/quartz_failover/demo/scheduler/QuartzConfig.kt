package com.quartz_failover.demo.scheduler

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import org.springframework.scheduling.quartz.SpringBeanJobFactory

@Configuration
class QuartzConfig {
    @Bean
    fun schedulerFactoryBean(): SchedulerFactoryBean {
        val schedulerFactoryBean = SchedulerFactoryBean()
        schedulerFactoryBean.setJobFactory(SpringBeanJobFactory())
        schedulerFactoryBean.setOverwriteExistingJobs(true)
        schedulerFactoryBean.setWaitForJobsToCompleteOnShutdown(true)
        return schedulerFactoryBean
    }
}
