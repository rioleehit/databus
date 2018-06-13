package com.hqxc.databus.client;
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


import java.util.Properties;
import java.util.function.Supplier;
import java.io.FileInputStream;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.jedis.HostAndPort;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.List;

import com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer;
import com.linkedin.databus.client.DatabusHttpClientImpl;
import org.apache.log4j.Logger;
//import com.hqxc.databus.client.IConsumerSupplier;
import com.hqxc.databus.client.test.*;

public class HqxcClientMain
{
    static final String PERSON_SOURCE0 = "com.hqxc.events.test.test0";
    static final String PERSON_SOURCE1 = "com.hqxc.events.test.test1";

    public static ShardedJedisPool createPool(){
        ShardedJedisPool p = null;
        try{
            Properties prop = new Properties();
            FileInputStream fis = new FileInputStream("./conf/redis.properties");
            prop.load(fis);
            fis.close();

            String hosts = prop.getProperty("redis.hosts");
            System.out.println("hosts  " + hosts);

            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(Integer.valueOf(prop.getProperty("redis.pool.maxTotal")));
            config.setMaxWaitMillis(Long.valueOf(prop.getProperty("redis.pool.maxWaitMillis")));
            config.setMaxIdle(Integer.valueOf(prop.getProperty("redis.pool.maxIdle")));
            config.setTestOnBorrow(Boolean.valueOf(prop.getProperty("redis.pool.testOnBorrow")));
            //config.setTestOnReturn(Boolean.valueOf(prop.getProperty("redis.pool.testOnReturn")));

            List<JedisShardInfo> infoList = new ArrayList<JedisShardInfo>();
            Set<HostAndPort> clusterNodes = new HashSet<HostAndPort>();
            for (String s : hosts.split(",")) {
                String[] ss = s.split(":");
                if (ss.length < 2) { continue; }
                JedisShardInfo shardInfo = new JedisShardInfo(ss[0], Integer.parseInt(ss[1]), 500);
                infoList.add(shardInfo);
                clusterNodes.add(new HostAndPort(ss[0], Integer.parseInt(ss[1])));
            }
            p = new ShardedJedisPool(config, infoList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return p;
    }
    public static ShardedJedisPool s_pool = null;
    public static void main(String[] args) throws Exception
    {
        ClientImp c1 = new ClientImp(PERSON_SOURCE0,"192.168.137.154", 11115, new ClientImp.ConsumerSupplier(){
            private Test0Consumer ref = null;
            public AbstractDatabusCombinedConsumer get(){
                ref = new Test0Consumer(HqxcClientMain.s_pool.getResource(), PERSON_SOURCE0);
                return ref;
            }
            public void disposed(){if(ref!=null){ref.disposed();} }
        });
        s_pool = createPool();
        DatabusHttpClientImpl client = c1.startConsumer(args);
        if(client == null){return;}
        client.startAndBlock();
        c1.disposed();
        s_pool.close();
  }
}
