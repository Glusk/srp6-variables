#!/bin/bash

# 0 - Fetch command line arguments
release=$1
snapshot=$2
dryRun=${3:-false}

# 1 - Checkout to a new release branch; bump version to a new release
#     version and tag it
git checkout -b "$release-release"
mvn versions:set "-DnewVersion=$release" "-DgenerateBackupPoms=false"
# Build the project and aborts release on failure
mvn clean install
success=$?
git commit -am "$release"
git tag "$release"

# 2 - Checkout to the master branch; bump version to a new snapshot version
git checkout master
mvn versions:set "-DnewVersion=$snapshot" "-DgenerateBackupPoms=false"
git commit -am "Bumped project to a new snapshot version."

# Push the new snapshot version and release tag if there were no errors
if [ "$success" -eq 0 ] && [ "$dryRun" = false ]
then 
  git push origin "$release"
  git push origin master
else
  git reset HEAD~
  git checkout -- pom.xml
fi

# 3 - Cleanup: delete the release branch and local tag
git tag --delete "$release"
git branch -D "$release-release"
