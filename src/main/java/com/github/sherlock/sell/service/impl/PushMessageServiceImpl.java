package com.github.sherlock.sell.service.impl;

import com.github.sherlock.sell.config.WeChatAccountConfig;
import com.github.sherlock.sell.dto.OrderDTO;
import com.github.sherlock.sell.service.PushMessageService;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * Created by TangBin on 2017/9/5.
 */
@Service
@Slf4j
public class PushMessageServiceImpl implements PushMessageService {

  private final WxMpService wxMpService;

  private final WeChatAccountConfig weChatAccountConfig;


  @Autowired
  public PushMessageServiceImpl(WxMpService wxMpService, WeChatAccountConfig weChatAccountConfig) {
    this.wxMpService = wxMpService;
    this.weChatAccountConfig = weChatAccountConfig;
  }


  @Override
  public void orderStatus(OrderDTO orderDTO) {
    final WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder().build();
    templateMessage.setTemplateId(weChatAccountConfig.getTemplateId().get("orderStatus"));
    templateMessage.setToUser(orderDTO.getBuyerOpenid());

    List<WxMpTemplateData> dataList = Arrays.asList(
      new WxMpTemplateData("first", "one"),
      new WxMpTemplateData("keyword1", "k1"),
      new WxMpTemplateData("keyword2", orderDTO.getBuyerPhone()),
      new WxMpTemplateData("keyword3", orderDTO.getOrderId()),
      new WxMpTemplateData("keyword4", orderDTO.getOrderStatusEnum().getMsg()),
      new WxMpTemplateData("keyword5", "¥" + orderDTO.getOrderAmount()),
      new WxMpTemplateData("keyword6", "bye")
    );

    templateMessage.setData(dataList);
    try {
      wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    } catch (WxErrorException e) {
      log.error("#weChat template message# send message failed, {} ", e);
    }

  }
}
