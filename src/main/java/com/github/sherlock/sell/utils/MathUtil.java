package com.github.sherlock.sell.utils;

/**
 * Created by TangBin on 2017/8/31.
 */

public class MathUtil {

  private static final Double MONEY_RANGE = 0.01;

  private MathUtil() {
  }

  public static <T extends Number, R extends Number> Boolean priceEquals(T t, R r) {
    return priceEquals(t.doubleValue(), r.doubleValue());
  }

  private static Boolean priceEquals(Double d1, Double d2) {
    final double dif = Math.abs(d1 - d2);
    return dif < MONEY_RANGE;
  }


}
