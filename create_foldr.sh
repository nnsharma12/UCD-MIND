#!/bin/bash
# create folders with specific substring 

for i in `cat subj_list`
do
mkdir -p "$i"_pairedfromT1andT3/surf
done 

