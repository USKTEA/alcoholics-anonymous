package com.quartz_failover.demo.scheduler

import jakarta.annotation.PostConstruct
import java.util.TimeZone
import org.quartz.CronScheduleBuilder
import org.quartz.JobBuilder
import org.quartz.Scheduler
import org.quartz.TriggerBuilder
import org.springframework.context.annotation.Configuration

@Configuration
class JobConfig(
    private val scheduler: Scheduler,
    private val retryJobListener: RetryJobListener,
) {
    @PostConstruct
    fun schedule() {
        val job = JobBuilder.newJob()
            .ofType(SimpleJob::class.java)
            .withIdentity("Simple Job")
            .build()

        val trigger = TriggerBuilder.newTrigger()
            .withIdentity("Trigger")
            .usingJobData("page", 0)
            .startNow()
            .withSchedule(
                CronScheduleBuilder.cronSchedule("0 11 22 * * ?")
                    .inTimeZone(TimeZone.getTimeZone("Asia/Seoul"))
            )
            .build()

        scheduler.scheduleJob(job, trigger)
        scheduler.listenerManager.addJobListener(retryJobListener)
    }
}
