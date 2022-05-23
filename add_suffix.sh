#!/bin/bash
### https://stackoverflow.com/questions/1108527/recursively-add-file-extension-to-all-files
### http://andykdocs.de/development/Linux/2013-09-04+Replace+a+substring+in+filenames+recursively


for f in $(ls -I add_suffix.sh -I subject_list -I fsaverage); do mv "$f" "$f"_pairedfromT1; done 
