package com.lanltn.android_core_helper.utils;

public class DistanceUtils {

    /**
     * Distance from 2 point
     *
     * @param lat1 latitute of point 1
     * @param lon1 longitute of point 1
     * @param lat2 latitute of point 2
     * @param lon2 longitute of point 2
     * @return distance from 2 point in meters
     */
    public static double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) *
                Math.sin(deg2rad(lat2)) +
                Math.cos(deg2rad(lat1)) *
                        Math.cos(deg2rad(lat2)) *
                        Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1609.344;
        return (dist);
    }

    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }

    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
}
