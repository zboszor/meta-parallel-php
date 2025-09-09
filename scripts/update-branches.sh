#!/bin/bash

git checkout main
git push origin HEAD

for i in styhead walnascar whinlatter ; do
	git checkout $i
	git merge origin/main
	git push origin HEAD
done

git checkout main
