#!/bin/bash

git checkout walnascar
git push origin HEAD

for i in styhead ; do
	git checkout $i
	git merge origin/main
	git push origin HEAD
done

git checkout walnascar
