package com.github.sherlock.sell.utils;

import java.util.Random;

/**
 * Created by TangBin on 2017/8/28.
 */

public class KeyUtil {

  private KeyUtil() {
  }


  /**
   * generate unique primary key format : timestamp + random number
   */
  public static
  synchronized //FIXME need attention!
  String genUniqueKey() {

    Random random = new Random();

    Integer randomNumber = random.nextInt(900000) + 100000;

    return System.currentTimeMillis() + String.valueOf(randomNumber);
  }



}
