package com.hqxc.databus.client.test;
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
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.ShardedJedis;
import org.json.JSONObject;
//import java.sql.*;


public class Test0Consumer extends AbstractDatabusCombinedConsumer
{
  public static final Logger LOG = Logger.getLogger(Test0Consumer.class.getName());

  public Test0Consumer(ShardedJedis jedis, String channel){
    m_jedis = jedis;
    m_channelName = channel;
    //LOG.info("connect redis: "+m_jedis.ping());
  }
  public void disposed(){
    m_jedis.close();
    m_jedis = null;
  }
  private String m_channelName;
  private ShardedJedis m_jedis = null;
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
    try {

      JSONObject j = new JSONObject();

      GenericRecord decodedEvent = eventDecoder.getGenericRecord(event, null);
      Long id = (Long)decodedEvent.get("id");
      Utf8 test_char = (Utf8)decodedEvent.get("test_char");
      Long test_int = (Long)decodedEvent.get("test_int");
      Utf8 test_text = (Utf8)decodedEvent.get("test_text");
      Float test_float = (Float)decodedEvent.get("test_float");
      Double test_double = (Double)decodedEvent.get("test_double");
      Long birthDate = (Long)decodedEvent.get("birthDate");
      Utf8 deleted = (Utf8)decodedEvent.get("deleted");

      if(m_jedis==null) {
        LOG.error("jedis is null");
        return ConsumerCallbackResult.ERROR;
      }
      Jedis[] jedisArray = new Jedis[]{};
      jedisArray = m_jedis.getAllShards().toArray(jedisArray);
      if(jedisArray.length == 0){
          LOG.error("jedis is empty");
          return ConsumerCallbackResult.ERROR;
      }
      Jedis tmpJ = jedisArray[0];

      JSONObject line = new JSONObject();
      line.put("id",id);
      line.put("test_char",test_char);
      line.put("test_int",test_int);
      line.put("test_text",test_text);
      line.put("test_float",test_float);
      line.put("test_double",test_double);
      line.put("birthDate",birthDate);
      line.put("deleted",deleted);

      tmpJ.publish(m_channelName, line.toString());

      LOG.info("line=" + line.toString());
    } catch (Exception e) {
      LOG.error("error decoding event ", e);
      return ConsumerCallbackResult.ERROR;
    }

    return ConsumerCallbackResult.SUCCESS;
  }

}
