package com.bs.domain.utils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @author J. Milton Chambi M.
 */
public class Util {
    
    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_PLAQUE_REGEX = 
            Pattern.compile("^[0-9]{2,4}[A-Z]{2,3}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_CELLPHONE_REGEX = 
            Pattern.compile("^[0-9]{8}$", Pattern.CASE_INSENSITIVE);
    public static final Pattern VALID_CI_REGEX = 
            Pattern.compile("^[0-9]{4,8}$", Pattern.CASE_INSENSITIVE);
    
    public static double roundTwoDecimals(double number) {
        return new BigDecimal(String.valueOf(number)).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
    }
    
    public static double roundTwoDecimalsStep05(double number) {
        return Math.round(number * 2) / 2.0;
    }
    
    public static boolean isNumber(String cad) {
        try {
            Integer.parseInt(cad);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }
    
    public static boolean isEmail(String cad) {
        if (cad != null && cad.length() > 0) {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(cad);
            return matcher.find();
        }
        return false;
    }
    
    public static boolean isPlaque(String cad) {
        if (cad != null && cad.length() > 0) {
            Matcher matcher = VALID_PLAQUE_REGEX .matcher(cad.toUpperCase());
            return matcher.find();
        }
        return false;
    }
    
    public static boolean isCellphone(String cad) {
        if (cad != null && cad.length() > 0) {
            Matcher matcher = VALID_CELLPHONE_REGEX .matcher(cad.toUpperCase());
            return matcher.find();
        }
        return false;
    }
    
    public static boolean isCI(String cad) {
        if (cad != null && cad.length() > 0) {
            Matcher matcher = VALID_CI_REGEX .matcher(cad.toUpperCase());
            return matcher.find();
        }
        return false;
    }

    public static String getLatestImageCar(List<String> images) {
        String selected = null;
        Long latestDate = 0L;
        for (String item: images) {
            Long datetime = Long.parseLong(item.split("-")[1].split("\\.")[0]);
            if (datetime.compareTo(latestDate) > 0) {
                latestDate = datetime;
                selected = item;
            }
        }
        return selected;
    }

}
