#!/bin/bash
n=4294967296
if [ $# == 1 ] ; then 
	num1=$[ $1/$n ]
	num2=$[ $1%$n ]
	echo $num1 $num2	
elif [ $# == 2 ]; then 
	let i=$1*$n+$2
	echo $i
else
	#let i=$1*$n+$2
	echo "wrong parameter（参数错误）"
fi
