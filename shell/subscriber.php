<?php
/*
echo "channel subscriber \n";
'redis' => array(
        'cluster' => true,
        'default' => array(
            'host'     => '127.0.0.1',
            'port'     => 7000
        ),
    )*/
$redis = new Redis();
$redis->connect('127.0.0.1',7000);
$channel1 = 'com_hqxc_events_test_test_0'; // channel
function callback0($redis, $channel, $message) {
	echo $channel.'\t'.'callback0'.$message;
}
$redis->subscribe([$channel1], 'callback0');

function callback1($redis, $channel, $message) {
	echo $channel.'\t'.'callback1'.$message;
}
$channel2 = 'com.hqxc.events.test.test_1'; // channel
$redis->subscribe([$channel2], callback1);


print_r("end");
