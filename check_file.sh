#!/bin/bash
###also can try https://stackoverflow.com/questions/59838/check-if-a-directory-exists-in-a-shell-script


ip=$1
for i in `cat "$ip"`
do
if [ -r $i ]; then
echo "file $i exists"
else
echo "file $i doesn't exist"
fi
done
