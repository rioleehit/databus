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


import com.linkedin.databus.client.DatabusHttpClientImpl;

public class PersonClientMain
{
  static final String PERSON_SOURCE = "com.linkedin.events.example.or_test.Person";
  static final String PERSON_SOURCE2 = "com.linkedin.events.example.or_test.Person2";

  public static void main(String[] args) throws Exception
  {
    DatabusHttpClientImpl.Config configBuilder = new DatabusHttpClientImpl.Config();

    //Try to connect to a relay on localhost
    configBuilder.getRuntime().getRelay("1").setHost("localhost");
    configBuilder.getRuntime().getRelay("1").setPort(11115);
//    configBuilder.getRuntime().getRelay("1").setSources(PERSON_SOURCE);
    configBuilder.getRuntime().getRelay("1").setSources(PERSON_SOURCE2);

    //Instantiate a client using command-line parameters if any
    DatabusHttpClientImpl client = DatabusHttpClientImpl.createFromCli(args, configBuilder);

//    //register callbacks
//    PersonConsumer personConsumer = new PersonConsumer();
//    client.registerDatabusStreamListener(personConsumer, null, PERSON_SOURCE);
//    client.registerDatabusBootstrapListener(personConsumer, null, PERSON_SOURCE);

    //register callbacks
    Person2Consumer person2Consumer = new Person2Consumer();
    client.registerDatabusStreamListener(person2Consumer, null, PERSON_SOURCE2);
    client.registerDatabusBootstrapListener(person2Consumer, null, PERSON_SOURCE2);

    //fire off the Databus client
    client.startAndBlock();
  }

}
