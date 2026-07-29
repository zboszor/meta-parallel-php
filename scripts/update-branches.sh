#!/bin/bash

MAINBRANCH=main

git checkout "$MAINBRANCH"
git push origin HEAD

for i in blacksail ; do
	git checkout $i
	git merge "origin/$MAINBRANCH"
	git push origin HEAD
done

git checkout "$MAINBRANCH"
