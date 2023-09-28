package com.study.companytracker.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CompanyTrackerLogger {

    private static final Logger CompanyTrackerLogger = LoggerFactory.getLogger(CompanyTrackerLogger.class);

    public static Logger LOGGER(){
        return CompanyTrackerLogger;
    }
}
