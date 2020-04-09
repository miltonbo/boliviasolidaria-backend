package com.bs.domain.utils;

import java.math.BigDecimal;

/**
 *
 * @author Milton BO
 */
public class NumberUtil {
 
    public static String formatTwoDecimal(BigDecimal number) {
        if (number != null) {
            number = number.setScale(2, BigDecimal.ROUND_HALF_UP);
            return number.toString();
        }
        return "0.00";
    }
    
    public static String formatTwoDecimal(double number) {
        BigDecimal result = BigDecimal.valueOf(number);
        result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
        return result.toString();
    }

    public static String ratingAverage(float rating1, float rating2, float rating3) {
        return formatTwoDecimal(BigDecimal.valueOf((rating1 + rating2 + rating3) / 3));
    }

}
