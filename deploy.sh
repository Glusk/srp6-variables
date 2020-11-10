#!/bin/bash

gpg --fast-import .travis/gpg.asc
mvn deploy -P publish -DskipTests=true --settings ".travis/maven-settings.xml" -B
