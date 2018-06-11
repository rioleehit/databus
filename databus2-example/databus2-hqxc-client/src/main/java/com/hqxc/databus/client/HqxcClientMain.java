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


import com.linkedin.databus.client.consumer.AbstractDatabusCombinedConsumer;
import com.linkedin.databus.client.DatabusHttpClientImpl;
import org.apache.log4j.Logger;
import  com.hqxc.databus.client.test.*;

public class HqxcClientMain
{
  static final String PERSON_SOURCE0 = "com.hqxc.events.test.test_0";
  static final String PERSON_SOURCE1 = "com.hqxc.events.test.test_1";

  public static final Logger LOG = Logger.getLogger(HqxcClientMain.class.getName());

  public static  DatabusHttpClientImpl startConsumer(String[] args, String source,AbstractDatabusCombinedConsumer consumer){
    try {
      DatabusHttpClientImpl.Config configBuilder = new DatabusHttpClientImpl.Config();

      //Try to connect to a relay on localhost
      configBuilder.getRuntime().getRelay("1").setHost("localhost");
      configBuilder.getRuntime().getRelay("1").setPort(11115);
      configBuilder.getRuntime().getRelay("1").setSources(source );

      //Instantiate a client using command-line parameters if any
      DatabusHttpClientImpl client = DatabusHttpClientImpl.createFromCli(args, configBuilder);

      //register callbacks
      //Test0Consumer test0Consumer = new Test0Consumer();
      client.registerDatabusStreamListener(consumer, null, source);
      client.registerDatabusBootstrapListener(consumer, null, source);
      //fire off the Databus client
      //client.startAndBlock();
        return client;
    }catch (Exception e){
      LOG.error("error ", e);
    }
    return null;
  }

  public static void main(String[] args)
  {
    try {
      DatabusHttpClientImpl c0 = startConsumer(args, PERSON_SOURCE0, new Test0Consumer());
      DatabusHttpClientImpl c1 = startConsumer(args, PERSON_SOURCE1, new Test1Consumer());
      if(c0!=null){c0.awaitShutdown();}
      if(c1!=null){c1.awaitShutdown();}
      /*
      DatabusHttpClientImpl.Config configBuilder = new DatabusHttpClientImpl.Config();

      //Try to connect to a relay on localhost
      configBuilder.getRuntime().getRelay("1").setHost("localhost");
      configBuilder.getRuntime().getRelay("1").setPort(11115);
//    configBuilder.getRuntime().getRelay("1").setSources(PERSON_SOURCE0);
      configBuilder.getRuntime().getRelay("1").setSources({PERSON_SOURCE0} );
      //configBuilder.getRuntime().getRelay("1").setSources(PERSON_SOURCE1);

      //Instantiate a client using command-line parameters if any
      DatabusHttpClientImpl client = DatabusHttpClientImpl.createFromCli(args, configBuilder);

//    //register callbacks
//    PersonConsumer personConsumer = new PersonConsumer();
//    client.registerDatabusStreamListener(personConsumer, null, PERSON_SOURCE);
//    client.registerDatabusBootstrapListener(personConsumer, null, PERSON_SOURCE);

      //register callbacks
      Test0Consumer test0Consumer = new Test0Consumer();
      client.registerDatabusStreamListener(test0Consumer, null, PERSON_SOURCE0);
      client.registerDatabusBootstrapListener(test0Consumer, null, PERSON_SOURCE0);
      Test1Consumer test1Consumer = new Test1Consumer();
      client.registerDatabusStreamListener(test1Consumer, null, PERSON_SOURCE1);
      client.registerDatabusBootstrapListener(test1Consumer, null, PERSON_SOURCE1);

      //fire off the Databus client
      client.startAndBlock();
      */
    }catch (Exception e){
      LOG.error("error ", e);
    }
  }

}
