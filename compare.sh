#!/bin/bash
#compare 2 files 
#-1 supresses lines that are unique to file 1
#-2 supresses lines unique to file 2
#-3 supresses lines unique/presnet in both files 1 and file 2 

comm -13 file_1 file_2
