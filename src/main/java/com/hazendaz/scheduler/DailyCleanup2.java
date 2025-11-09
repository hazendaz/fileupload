/*
 * fileupload (https://github.com/hazendaz/fileupload)
 *
 * Copyright 2009-2025 Hazendaz.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of The Apache Software License,
 * Version 2.0 which accompanies this distribution, and is available at
 * https://www.apache.org/licenses/LICENSE-2.0.txt
 *
 * Contributors:
 *     Hazendaz (Jeremy Landis).
 */
package com.hazendaz.scheduler;

import com.hazendaz.beans.InventoryItem;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;

import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

@Scheduled(cronExpression = "0/10 * * * * ?")
public class DailyCleanup2 implements Job {

    @Inject
    private Logger logger;

    @Inject
    private InventoryItem inventoryItem;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {

        final ContextControl ctxCtrl = BeanProvider.getContextualReference(ContextControl.class);

        // this will implicitly bind a new RequestContext to the current thread
        ctxCtrl.startContext(RequestScoped.class);

        try {
            // Say Hello to the World and display the date/time
            this.logger.info("Hello World! - Job 2 {}", Long.valueOf(System.currentTimeMillis()));
            this.logger.info("logBean info {}", this.inventoryItem);
        } finally {
            // stop the RequestContext to ensure that all request-scoped beans get cleaned up.
            ctxCtrl.stopContext(RequestScoped.class);
        }
    }

}
