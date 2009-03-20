#!/bin/sh
#git-export-bundle.sh
#Creates a bundle of all git revisions 
#Call with the filename as argument
git bundle create $@ `git rev-list --parents master | grep '^.\{40\/}$'` HEAD