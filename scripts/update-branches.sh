#!/bin/bash

git checkout scarthgap
git push origin HEAD

for i in kirkstone langdale mickledore nanbield ; do
	git checkout $i
	git merge origin/scarthgap
	git push origin HEAD
done

git checkout scarthgap
