#!/bin/bash


for dir in $(ls -d */); do
        mkdir "$dir/surf"
done
