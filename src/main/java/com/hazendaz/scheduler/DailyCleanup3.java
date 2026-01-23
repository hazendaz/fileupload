/*
 * SPDX-License-Identifier: Apache-2.0
 * Copyright 2009-2026 Hazendaz
 */
package com.hazendaz.scheduler;

import jakarta.annotation.PostConstruct;
import jakarta.inject.Inject;

import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

@Scheduled(cronExpression = "0/15 * * * * ?")
public class DailyCleanup3 implements Job {

    @Inject
    private Logger logger;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {
        // Say Hello to the World and display the date/time
        this.logger.info("Hello World! - Job 3 {}", Long.valueOf(System.currentTimeMillis()));
    }

    @PostConstruct
    public void init() {
        this.logger.info("This is working");
    }

}
