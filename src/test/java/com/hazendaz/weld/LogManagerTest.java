package com.hazendaz.weld;

import javax.inject.Inject;

import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.junit5.WeldInitiator;
import org.jboss.weld.junit5.WeldJunit5Extension;
import org.jboss.weld.junit5.WeldSetup;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;

@Disabled
@ExtendWith(WeldJunit5Extension.class)
public class LogManagerTest {

    @WeldSetup
    public WeldInitiator weld = WeldInitiator.of(new Weld());

    @Inject
    private Logger logger;

    @Test
    public void getLoggerTest() {
        this.logger.info("logger worked");
    }

}
