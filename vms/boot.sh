#!/bin/bash
set -x 
for i in $(ls -1 | grep -v boot | grep -v mods);
do
  cd $i 
  vagrant up
  cd ..
done
