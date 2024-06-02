package com.quartz_failover.demo.scheduler

import java.util.Date
import org.quartz.JobExecutionContext
import org.quartz.JobExecutionException
import org.quartz.SimpleScheduleBuilder
import org.quartz.TriggerBuilder
import org.quartz.listeners.JobListenerSupport
import org.springframework.stereotype.Component

@Component
class RetryJobListener : JobListenerSupport() {

    override fun getName(): String {
        return "RetryJobListener"
    }

    override fun jobWasExecuted(context: JobExecutionContext, jobException: JobExecutionException?) {
        if (jobException != null) {
            val jobDetail = context.jobDetail
            val jobDataMap = jobDetail.jobDataMap
            val currentPage = jobDataMap.getInt("page")
            val scheduler = context.scheduler

            val trigger = TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .usingJobData("page", currentPage)
                .startAt(Date(System.currentTimeMillis() + 5000)) // 5 seconds later
                //.startNow(Date(System.currentTimeMillis() + 600000)) //10 minutes later
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build()

            scheduler.scheduleJob(trigger)
        }
    }
}
