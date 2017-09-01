package com.github.sherlock.sell.utils;

import com.github.sherlock.sell.enums.EnumMessage;
import com.github.sherlock.sell.enums.ResultEnum;
import com.github.sherlock.sell.exception.SellException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.apache.commons.lang3.EnumUtils;

/**
 * Created by TangBin on 2017/9/1.
 */

public class EnumUtil {

  private EnumUtil() {
  }

  public static <T extends Enum & EnumMessage> T getByCode(Integer code,
      Class<T> clazz) {

    final List<T> enumList = EnumUtils.getEnumList(clazz);

    final Optional<T> first = enumList.stream()
        .filter(it -> Objects.equals(it.getCode(), code)).findFirst();

    if (first.isPresent()) {
      return first.get();
    } else {
      throw new SellException(ResultEnum.PARA_ERROR);
    }
  }
}
