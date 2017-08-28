package com.github.sherlock.sell.utils;

import com.github.sherlock.sell.vo.ResultVO;

/**
 * Created by TangBin on 2017/8/28.
 */

public class ResultVOUtil {

  private ResultVOUtil() {
  }

  /**
   *
   * @param o
   * @return
   */
  public static ResultVO success(Object o) {
    ResultVO objectResultVO = new ResultVO();
    objectResultVO.setData(o);
    objectResultVO.setCode(0);
    objectResultVO.setMsg("success");
    return objectResultVO;
  }

  public static ResultVO success() {
    return success(null);
  }

  public static final ResultVO error(Integer code, String msg) {
    ResultVO resultVO = new ResultVO();
    resultVO.setCode(code);
    resultVO.setMsg(msg);
    return resultVO;
  }

}
