#!/bin/bash

MAINBRANCH=wrynose

git checkout "$MAINBRANCH"
git push origin HEAD

for i in whinlatter ; do
	git checkout $i
	git merge "origin/$MAINBRANCH"
	git push origin HEAD
done

git checkout "$MAINBRANCH"
