package com.github.sherlock.sell.utils;

import com.github.sherlock.sell.vo.ResultVO;

/**
 * Created by TangBin on 2017/8/28.
 */

public class ResultVOUtil {

  private ResultVOUtil() {
  }

  /**
   * @param o
   * @return
   */
  public static <T> ResultVO<T> success(T o) {
    ResultVO<T> objectResultVO = new ResultVO<>();
    objectResultVO.setData(o);
    objectResultVO.setCode(0);
    objectResultVO.setMsg("success");
    return objectResultVO;
  }

  public static ResultVO success() {
    return success(null);
  }

  /**
   * @param code
   * @param msg
   * @return
   */
  public static ResultVO error(Integer code, String msg) {
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }

}
