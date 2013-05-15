#!/bin/bash
set -x 
for i in $(ls -1 | grep -v boot);
do
  cd $i 
  vagrant up
  cd ..
done
