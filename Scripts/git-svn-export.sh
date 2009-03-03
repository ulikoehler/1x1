#!/bin/sh
git svn init --prefix=svn/ $@
git svn fetch
echo `git rev-list --parents master | grep '^.\{40\}$'` `git rev-parse svn/trunk`   >> .git/info/grafts
git filter-branch -- ^svn/trunk --all
rm .git/info/grafts
git svn rebase