#!/bin/bash
# converts items in column to row 

{ for i in `cat colm_list`  
do echo -n "'$i';" 
done } > out.txt
