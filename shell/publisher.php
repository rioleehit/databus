<?php

$redis = new Redis();
$redis->connect('127.0.0.1',7000);
$channel ='com_hqxc_events_test_test_0';
$msg = 'message';
$redis->publish($channel, $msg);
