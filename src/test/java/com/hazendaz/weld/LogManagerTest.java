package com.hazendaz.weld;

import javax.inject.Inject;

import org.apache.deltaspike.testcontrol.api.junit.CdiTestRunner;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;

@Ignore
@RunWith(CdiTestRunner.class)
public class LogManagerTest {

    @Inject
    private Logger logger;

    @Test
    public void getLoggerTest() {
        this.logger.info("logger worked");
    }

}
