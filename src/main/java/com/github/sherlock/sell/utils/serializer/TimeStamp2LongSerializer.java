package com.github.sherlock.sell.utils.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Created by TangBin on 2017/8/29.
 */
//FIXME need attention!
public class TimeStamp2LongSerializer extends JsonSerializer<Timestamp> {

  @Override
  public void serialize(Timestamp timestamp, JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
    jsonGenerator.writeNumber(timestamp.getTime() / 1000);
  }
}
