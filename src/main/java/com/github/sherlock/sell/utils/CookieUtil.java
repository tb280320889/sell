package com.github.sherlock.sell.utils;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by TangBin on 2017/9/4.
 */

public class CookieUtil {
  private CookieUtil() {
  }

  /**
   *
   * @param response
   * @param name
   * @param value
   * @param maxAge
   */
  public static void set(HttpServletResponse response, String name, String value, Integer maxAge) {

    final Cookie cookie = new Cookie(name, value);
    cookie.setPath("/");
    cookie.setMaxAge(maxAge);
    response.addCookie(cookie);
  }

  /**
   *
   * @param request
   * @param name
   * @return
   */
  public static Cookie get(HttpServletRequest request, String name) {
    final Map<String, Cookie> cookieMap = readCookieMap(request);
    if (cookieMap.containsKey(name)) {
      return cookieMap.get(name);
    }
    return null;
  }

  private static Map<String, Cookie> readCookieMap(HttpServletRequest request) {
    final HashMap<String, Cookie> cookieMap = new HashMap<>();
    final Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        cookieMap.put(cookie.getName(), cookie);
      }
    }
    return cookieMap;
  }

}
