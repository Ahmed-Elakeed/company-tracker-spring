package com.study.companytracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @Author Ahmed Elakeed
 * Singleton Application Logger
 */
public class CompanyTrackerLogger {

    // Initialize final logger instance
    private static final Logger CompanyTrackerLogger = LoggerFactory.getLogger(CompanyTrackerLogger.class);


    /**
     * @Author Ahmed Elakeed
     * @return single logger instance to use
     */
    public static Logger LOGGER(){
        return CompanyTrackerLogger;
    }
}
