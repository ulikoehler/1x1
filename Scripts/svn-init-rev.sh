#!/bin/sh
#A script to create the inital directories (trunk, tags and branches) in a Subversion repository.
#Runs only on (Lin|U)n(i|u)x systems.
#Call with the usual svn checkout arguments (but not a directory name to checkout to)
svn checkout $@ /dev/shm/svntmp/
pushd .
cd /dev/shm/svntmp
mkdir trunk tags branches
svn add trunk tags branches
svn commit -m "Added trunk, tags and branches directory"
popd
rm -rf /dev/shm/svntmp
