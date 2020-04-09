package com.bs.domain.utils;

import lombok.NoArgsConstructor;

/**
 *
 * @author J. Milton Chambi M.
 */
@NoArgsConstructor
public class SystemPropertyUtil {

    private static String PLATFORM = "boliviasolidaria";

    public static String getResourcesPath() {
        return System.getProperty(PLATFORM + ".resources.path");
    }

    public static String getBoliviaSolidariaUrl() {
        return System.getProperty(PLATFORM + ".url");
    }

    public static void setPlatform(String platform) {
        PLATFORM = platform;
    }

}
