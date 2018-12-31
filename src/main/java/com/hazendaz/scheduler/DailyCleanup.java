package com.hazendaz.scheduler;

import com.hazendaz.beans.InventoryItem;
import com.hazendaz.model.UserList;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import org.apache.deltaspike.cdise.api.ContextControl;
import org.apache.deltaspike.core.api.provider.BeanProvider;
import org.apache.deltaspike.scheduler.api.Scheduled;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;

@Scheduled(cronExpression = "0/5 * * * * ?")
public class DailyCleanup implements Job {

    @Inject
    private Logger logger;

    @Inject
    private InventoryItem inventoryItem;

    @Inject
    private UserList userList;

    @Override
    public void execute(final JobExecutionContext context) throws JobExecutionException {

        final ContextControl ctxCtrl = BeanProvider.getContextualReference(ContextControl.class);

        // this will implicitly bind a new RequestContext to the current thread
        ctxCtrl.startContext(RequestScoped.class);

        try {
            // Say Hello to the World and display the date/time
            this.logger.info("Hello World! - Job 1 {}", Long.valueOf(System.currentTimeMillis()));
            this.logger.info("logBean info {}", this.inventoryItem);
            this.logger.info("userList size {}", Integer.valueOf(this.userList.getUserList().size()));
            if (this.userList.getUserList().size() > 0) {
                this.logger.info("user 1 is {}", this.userList.getUserList().get(0).getName());
            }
        } finally {
            // stop the RequestContext to ensure that all request-scoped beans get cleaned up.
            ctxCtrl.stopContext(RequestScoped.class);
        }
    }

}
