package com.github.sherlock.sell.handler;

import com.github.sherlock.sell.config.ProjectUrlConfig;
import com.github.sherlock.sell.exception.SellException;
import com.github.sherlock.sell.exception.SellerAuthorizeException;
import com.github.sherlock.sell.utils.ResultVOUtil;
import com.github.sherlock.sell.vo.ResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by TangBin on 2017/9/5.
 */

@ControllerAdvice
public class SellExceptionHandler {

  private final ProjectUrlConfig projectUrlConfig;

  @Autowired
  public SellExceptionHandler(ProjectUrlConfig projectUrlConfig) {
    this.projectUrlConfig = projectUrlConfig;
  }

  /**
   * intercept exception while logging
   */
  @ExceptionHandler(SellerAuthorizeException.class)
  @ResponseStatus(HttpStatus.FORBIDDEN)
  public ModelAndView handlerAuthorizeException() {
    return new ModelAndView("redirect:"
      .concat(projectUrlConfig.getWeChatOpenAuthorize())
      .concat("sell/weChat/qrAuthorize")
      .concat("?returnUrl=")
      .concat(projectUrlConfig.getSellUrl())
      .concat("sell/seller/login"));

  }


  @ExceptionHandler(SellException.class)
  @ResponseBody
  public ResultVO handlerSellerException(SellException e) {
    return ResultVOUtil.error(e.getCode(), e.getMessage());
  }


}
