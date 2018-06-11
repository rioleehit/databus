package com.hqxc.databus.relay;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.linkedin.databus.container.netty.HttpRelay;
import com.linkedin.databus.core.data_model.PhysicalPartition;
import com.linkedin.databus.core.util.InvalidConfigException;
import com.linkedin.databus2.core.DatabusException;
import com.linkedin.databus2.core.seq.MultiServerSequenceNumberHandler;
import com.linkedin.databus2.producers.EventProducer;
import com.linkedin.databus2.relay.DatabusRelayMain;
import com.linkedin.databus2.relay.config.PhysicalSourceStaticConfig;

import com.hqxc.databus.relay.test.TestRelayServer;

public class HqxcRelayServer{// extends DatabusRelayMain
 // public static final String MODULE = TestRelayServer.class.getName();
  //public static final Logger LOG = Logger.getLogger(MODULE);
 // static final String FULLY_QUALIFIED_PERSON_EVENT_NAME = "com.linkedin.events.example.or_test.Person";
  //static final int PERSON_SRC_ID = 40;

//  MultiServerSequenceNumberHandler _maxScnReaderWriters;
//  protected Map<PhysicalPartition, EventProducer> _producers;
//
//  public TestRelayServer() throws IOException, InvalidConfigException, DatabusException
//  {
//    this(new HttpRelay.Config(), null);
//  }
//
//  public TestRelayServer(HttpRelay.Config config, PhysicalSourceStaticConfig [] pConfigs)
//  throws IOException, InvalidConfigException, DatabusException
//  {
//    this(config.build(), pConfigs);
//  }
//
//  public TestRelayServer(HttpRelay.StaticConfig config, PhysicalSourceStaticConfig [] pConfigs)
//  throws IOException, InvalidConfigException, DatabusException
//  {
//    super(config, pConfigs);
//
//  }

  public static void main(String[] args) throws Exception
  {
      TestRelayServer.test(args);
//     Cli cli = new Cli();
//     cli.setDefaultPhysicalSrcConfigFiles("conf/sources-or-person.json");
//     cli.processCommandLineArgs(args);
//     cli.parseRelayConfig();
//     // Process the startup properties and load configuration
//     PhysicalSourceStaticConfig[] pStaticConfigs = cli.getPhysicalSourceStaticConfigs();
//     HttpRelay.StaticConfig staticConfig = cli.getRelayConfigBuilder().build();
//
//     // Create and initialize the server instance
//     DatabusRelayMain serverContainer = new DatabusRelayMain(staticConfig, pStaticConfigs);
//
//     serverContainer.initProducers();
//     serverContainer.registerShutdownHook();
//     serverContainer.startAndBlock();
  }

}
