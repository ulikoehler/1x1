#!/bin/sh
svn checkout $@ /dev/shm/svntmp/
pushd .
cd /dev/shm/svntmp
mkdir trunk tags branches
svn add trunk tags branches
svn commit -m "Added trunk, tags and branches directory"
popd
rm -rf /dev/shm/svntmp
