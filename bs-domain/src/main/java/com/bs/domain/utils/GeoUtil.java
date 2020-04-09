/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bs.domain.utils;

/**
 *
 * @author jmcm
 */
public class GeoUtil {
    
    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.hypot(x2-x1, y2-y1);
    }
    
}
