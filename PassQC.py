#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
Created on Thu Feb 14 09:47:47 2019

@author: cory
"""

def Scanid_to_STS(scanid):
    pattern = re.compile("^([0-9]{6}[\-\_][0-9]{3})[\_]?[0-9]?(p?)")
    result=pattern.match(scanid)   
    if not result:
        print("No result Scanid_to_STS", scanid)
        exit()
    else:
        return result.group(1)
    
def Scanid_to_TimepointID(scanid):    
    pattern = re.compile("^[0-9]{6}[\-\_][0-9]{3}[\_]?([0-9]?p?)")
    result=pattern.match(scanid)   
    if not result: 
        print("No result Scanid_to_Timepoint \n",scanid)
        exit()
    
    if (result.group(1)=='') or result.group(1)=='0' : return ''
    elif result.group(1) in ['1', '2', '3']:  return int(result.group(1))   



AwaitingQC='4_BrainGPS_Outputs_wt/3_AwaitingQC/'

#AwaitingQC='/data/arch/WildThings/4_BrainGPS_Outputs_wt/3_AwaitingQC/'
import sys, os, re
if len(sys.argv)!=2 :
    print("No arguments provided.  You hafta tell me which scan has failed QC")
    exit()

scan_name=sys.argv[1]
scanpath=os.path.join(AwaitingQC,sys.argv[1])
#print (scanpath)


if not os.path.exists(scanpath):
    print("\n\n Sorry to break the news, but that scan doesn't exist in AwaitingQC. \n Not ", scan_name)
    exit()
else:
   sts=Scanid_to_STS(scan_name)
   tp=Scanid_to_TimepointID(scan_name)
   print("STS+tp=",sts+"_"+str(tp))
   
   destination='4_BrainGPS_Outputs_wt/4_PassedQC/'
   import glob
  
   hits=glob.glob(destination+sts+'*')
   
   if len(hits)>0:
       for hit in hits: 
           #print('detected', hit)
           hit_sts=Scanid_to_STS(os.path.basename(hit))
           hit_tp=Scanid_to_TimepointID(os.path.basename(hit))
           if (sts==hit_sts and tp==hit_tp):
               print ("No Can Do.   We've already passed an atlas for this scan:")
               print ("\t",hit)
               exit()
   
   
   
   
   import shutil
   shutil.move(scanpath,destination)
   
   
   import logging
   logging.basicConfig(filename='log_PassQC.txt', level=logging.DEBUG, format='%(asctime)s - %(levelname)s - %(message)s')
   logging.debug('FailQC ' + scan_name )
   print("Using log file: log_PassQC  " )
   print("QC Pass has been recorded for: ", scan_name)
    