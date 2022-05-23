#!/usr/bin/env python3
# -*- coding: utf-8 -*-
import os
import re
import glob
import sys

awaitingqc = '/Users/Natasha/Desktop/test_folder/awaitingqc'

#print('\nSCAN :', sys.argv[1])
scan_name=sys.argv[1]

#also can use this: ask cory which is better?
##for f in [os.path.basename(x)
##    for x in glob.glob(awaitingqc+'/' + scan_name + '*')]:
    
for f in glob.glob(awaitingqc+'/' + scan_name + '*'):

    #print(f)
    print(os.path.basename(f))
    #print('hello test')
	
    # pilot scan, contains 3p
    matchp = re.search(r'[0-9]{6}\-[0-9]{3}\_[0-9]{0,1}3p+', f)
    # simpler version: \d{6}\-\d{3}\_\d?3p+
    # where \d is shorthand for [0-9]
    # and ? is shorthand for {0,1}

    # time 1, contains _OwlOrient
    match1 = re.search(r'[0-9]{6}\-[0-9]{3}\_[A-Z]+', f)

    # time 2, contains _1
    match2 = re.search(r'[0-9]{6}\-[0-9]{3}\_[1]{1}', f)

    # time 3, contains _2
    match3 = re.search(r'[0-9]{6}\-[0-9]{3}\_[2]{1}', f)

    # time 4, contains _3
    match4 = re.search(r'[0-9]{6}\-[0-9]{3}\_[3]{1}', f)

    
    
    if matchp:
        print('Pattern',matchp.group(0), '\nPilot Scan')
    elif match1:
        print('Pattern',match1.group(0), '\nTime 1 \n')
    elif match2:
        print('Pattern',match2.group(0), '\nTime 2 \n')
    elif match3:
        print('Pattern',match3.group(0), '\nTime 3 \n')
    elif match4:
        print('Pattern',match4.group(0), '\nTime 4 \n')

##    else:
##        print('no STS# duplicates found')

