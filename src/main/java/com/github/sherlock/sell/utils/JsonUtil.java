package com.github.sherlock.sell.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by TangBin on 2017/8/31.
 */

public class JsonUtil {

  private JsonUtil() {
  }

  /**
   *
   * @param o
   * @return
   */
  public static final String toJson(Object o) {
    final GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.setPrettyPrinting();

    final Gson gson = gsonBuilder.create();
    return gson.toJson(o);

  }

}
