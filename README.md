# SRP-6 Variables

[![Build Status](https://travis-ci.com/Glusk/srp6-variables.svg?branch=master)](https://travis-ci.com/Glusk/srp6-variables)
[![Build status](https://ci.appveyor.com/api/projects/status/4dlyh0qkyd7aubpk/branch/master?svg=true)](https://ci.appveyor.com/project/Glusk/srp6-variables/branch/master)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/4b28e7a9389046a98c42f6a6eaa00ad8)](https://www.codacy.com/gh/Glusk/srp6-variables/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Glusk/srp6-variables&amp;utm_campaign=Badge_Grade)
[![Coverage Status](https://coveralls.io/repos/github/Glusk/srp6-variables/badge.svg?branch=master)](https://coveralls.io/github/Glusk/srp6-variables?branch=master)

[![LoC](https://tokei.rs/b1/github/glusk/srp6-variables)](https://github.com/Glusk/srp6-variables)
[![Hits-of-Code](https://hitsofcode.com/github/glusk/srp6-variables?branch=master)](https://hitsofcode.com/view/github/glusk/srp6-variables?branch=master)

A Java library of cryptographic primitives required for the SRP-6 protocol.

---

## Releases

Use the [release](./release.sh) script with the following arguments:

1.  `release` - the next release version

2.  `snapshot` - the next snapshot version

3.  `dryRun` (optional) - if set to `true`, the changes will not be pushed
   to the remote repository

Example:

``` bash
./release.sh 0.1.1 0.1.2-SNAPSHOT
```
