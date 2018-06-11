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
import java.sql.*;


public class Test0Consumer extends AbstractDatabusCombinedConsumer
{
  public static final Logger LOG = Logger.getLogger(Test0Consumer.class.getName());

  private Jedis jedis = null;

  // JDBC 驱动名及数据库 URL
  static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
  static final String DB_URL = "jdbc:mysql://localhost:3306/RUNOOB";

  // 数据库的用户名与密码，需要根据自己的设置
  static final String USER = "otis";
  static final String PASS = "123qwe";
  Connection conn = null;
  Statement stmt = null;

  public Test0Consumer(){
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


    try{
      // 注册 JDBC 驱动
      Class.forName("com.mysql.jdbc.Driver");

      // 打开链接
      LOG.info("连接数据库");
      conn = DriverManager.getConnection(DB_URL,USER,PASS);

      // 执行查询
      LOG.info(" 实例化Statement对象...");
      stmt = conn.createStatement();
      String sql;
      sql = "SELECT id, name, url FROM websites";
      ResultSet rs = stmt.executeQuery(sql);

      // 展开结果集数据库
      while(rs.next()){
        // 通过字段检索
        int id  = rs.getInt("id");
        String name = rs.getString("name");
        String url = rs.getString("url");

        // 输出数据
        LOG.info("ID: " + id);
        LOG.info(", 站点名称: " + name);
        LOG.info(", 站点 URL: " + url);
      }
      // 完成后关闭
      rs.close();
      stmt.close();
      conn.close();
    }catch(SQLException se){
      // 处理 JDBC 错误
      se.printStackTrace();
      LOG.error("error decoding event ", se);
    }catch(Exception e){
      // 处理 Class.forName 错误
      e.printStackTrace();
      LOG.error("error decoding event ", e);
    }finally{
      // 关闭资源
      try{
        if(stmt!=null) stmt.close();
      }catch(SQLException se2){
        LOG.error("error decoding event ", se2);
      }// 什么都不做
      try{
        if(conn!=null) conn.close();
      }catch(SQLException se){
        se.printStackTrace();
        LOG.error("error decoding event ", se);
        return ConsumerCallbackResult.ERROR;
      }
    }
    return ConsumerCallbackResult.SUCCESS;



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
