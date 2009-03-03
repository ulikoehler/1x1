#!/bin/sh
#This scripts backs up a Subversion repository by cloning it to a compressed bare git repository
#Call with the usual git svn clone options
#Works only on systems having /dev/shm enabled and enough memory to buffer the full uncompressed
#git directory, the git bare directory and the archive.
pushd .
mkdir /dev/shm/svnbackup
cd /dev/shm/svnbackup
git svn clone $@ repo
cd repo
git gc --aggressive
cd ..
git clone --bare repo repo.git
rm -rf repo
tar cjf repo.tar.bz2 repo.git
rm -rf repo.git
popd
cp /dev/shm/svnbackup/repo.tar.bz2 .
rm -rf /dev/shm/svnbackup