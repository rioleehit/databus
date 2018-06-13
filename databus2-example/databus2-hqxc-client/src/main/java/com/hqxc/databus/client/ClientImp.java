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

import com.hqxc.databus.client.test.*;

public class ClientImp
{
    public interface ConsumerSupplier {
        AbstractDatabusCombinedConsumer get();
        void disposed();
    }
    public ClientImp(String sEvt, String sHost, int iPort,ConsumerSupplier ConsumerCreator){
        m_Evt = sEvt;
        m_Host = sHost;
        m_Port = iPort;
        m_ConsumerCreator = ConsumerCreator;
    }

    public void disposed(){ m_ConsumerCreator.disposed();  }
    public DatabusHttpClientImpl startConsumer(String[] args){
        DatabusHttpClientImpl.Config configBuilder = new DatabusHttpClientImpl.Config();

        //Try to connect to a relay on localhost
        configBuilder.getRuntime().getRelay("1").setHost(m_Host);
        configBuilder.getRuntime().getRelay("1").setPort(m_Port);
        configBuilder.getRuntime().getRelay("1").setSources(m_Evt);

        //Instantiate a client using command-line parameters if any
        try{
            DatabusHttpClientImpl client = DatabusHttpClientImpl.createFromCli(args, configBuilder);
            AbstractDatabusCombinedConsumer consumer = m_ConsumerCreator.get();
            //register callbacks
            //Test0Consumer test0Consumer = new Test0Consumer();
            client.registerDatabusStreamListener(consumer, null, m_Evt);
            client.registerDatabusBootstrapListener(consumer, null, m_Evt);
            return client;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    protected String m_Evt;
    protected String m_Host;
    protected int m_Port;
    protected ConsumerSupplier m_ConsumerCreator;

}


