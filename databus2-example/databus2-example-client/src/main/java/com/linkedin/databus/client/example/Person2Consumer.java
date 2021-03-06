package com.linkedin.databus.client.example;
/*
 *
 * Copyright 2013 LinkedIn Corp. All rights reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
*/


import org.apache.avro.generic.GenericRecord;
import org.apache.avro.util.Utf8;
import org.apache.log4j.Logger;

import com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer;
import com.linkedin.databus.client.pub.ConsumerCallbackResult;
import com.linkedin.databus.client.pub.DbusEventDecoder;
import com.linkedin.databus.core.DbusEvent;

import redis.clients.jedis.Jedis;

public class Person2Consumer extends AbstractDatabusCombinedConsumer
{
  public static final Logger LOG = Logger.getLogger(Person2Consumer.class.getName());

  private Jedis jedis = null;
  public Person2Consumer(){
      jedis = new Jedis("localhost");
      LOG.info("connect redis: "+jedis.ping());
  }
  @Override
  public ConsumerCallbackResult onDataEvent(DbusEvent event,
                                            DbusEventDecoder eventDecoder)
  {
    return processEvent(event, eventDecoder);
  }

  @Override
  public ConsumerCallbackResult onBootstrapEvent(DbusEvent event,
                                                 DbusEventDecoder eventDecoder)
  {
    return processEvent(event, eventDecoder);
  }

  private ConsumerCallbackResult processEvent(DbusEvent event,
                                              DbusEventDecoder eventDecoder)
  {
    GenericRecord decodedEvent = eventDecoder.getGenericRecord(event, null);
    try {
      Long id = (Long)decodedEvent.get("id");
      Utf8 firstName = (Utf8)decodedEvent.get("firstName");
      Utf8 lastName = (Utf8)decodedEvent.get("lastName");
      Long birthDate = (Long)decodedEvent.get("birthDate");
      Utf8 deleted = (Utf8)decodedEvent.get("deleted");

      String json = "\"id\":" + id +
              ", \"firstName\": \"" + firstName.toString() +
              "\", \"lastName\": \"" + lastName.toString() +
              "\", \"birthDate\": " + birthDate +
              ", \"deleted\": \"" + deleted.toString()+"\"";
      LOG.info(json);
      if(jedis!=null){
          jedis.set("id"+id, json);
          LOG.info("redis save: "+ jedis.get("id"+id));
      }
    } catch (Exception e) {
      LOG.error("error decoding event ", e);
      return ConsumerCallbackResult.ERROR;
    }

    return ConsumerCallbackResult.SUCCESS;
  }

}
