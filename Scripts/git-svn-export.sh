#!/bin/sh
#A script to init Git->SVN exporting
#Call with the usual git svn init parameters
#After calling this script you can easily call git svn dcommit or so
#without getting any error messages.
#Remember that the SVN repository must have at least one revision.
#Use svn-init-rev.sh to create the initial revision (the script creates the trunk, tags and branches directory)
#WARNING:
#If the SVN repository doesn't have exactly one revision, it may lead to undefined behaviour
#so create a backup of the data
git svn init --prefix=svn/ $@
git svn fetch
echo `git rev-list --parents master | grep '^.\{40\}$'` `git rev-parse svn/trunk`   >> .git/info/grafts
git filter-branch -- ^svn/trunk --all
rm .git/info/grafts
git svn rebase