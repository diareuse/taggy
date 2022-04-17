<p align="center">
  <img src="art/logo.png" width="128px" />
</p>

<h1 align="center">Taggy</h1>

<p align="center">Make your tagging easier and predictable</p>

<p align="center">
    <a href="https://www.codacy.com/gh/diareuse/taggy/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=diareuse/taggy&amp;utm_campaign=Badge_Grade">
        <img src="https://app.codacy.com/project/badge/Grade/a30de5da7cc943a899a171eabd99ecc8"/>
    </a>
    <a href="https://www.codacy.com/gh/diareuse/taggy/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=diareuse/taggy&amp;utm_campaign=Badge_Coverage">
        <img src="https://app.codacy.com/project/badge/Coverage/a30de5da7cc943a899a171eabd99ecc8"/>
    </a>
</p>
<p align="center">
    <a href="https://github.com/diareuse/taggy/actions/workflows/coverage-report.yml">
        <img src="https://github.com/diareuse/taggy/workflows/Coverage%20Report/badge.svg"/>
    </a>
    <a href="https://github.com/diareuse/taggy/actions/workflows/build-artifacts.yml">
        <img src="https://github.com/diareuse/taggy/workflows/Build%20Artifacts%20(Native%20Images)/badge.svg"/>
    </a>
</p>

---

## Download

Navigate to [Releases]("http://github.com/diareuse/taggy/releases"), download binary per your platform.

## Usage

> Please note that `taggy` requires mandatory `pattern` parameter. You should create an alias depending on which pattern
> you would like to use.

```bash
# vanilla semantic versioning
alias tagsem="taggy --pattern \"([0-9]+\.)+[0-9]+(-[a-z]+[0-9]+)?\"" # 2.13.46 || 2.13.46.1 || 2.13.46-dev2

# year based versioning (does not conform with semver)
alias tagdate="taggy --pattern \"$(date +%Y)(\.[0-9]+)+(-[a-z]+[0-9]+)?\"" # 2022.10 || 2022.10.1 || 2022.10-dev2
```

```markdown
usage: taggy [-a <arg>] [--everywhere] [-f] [-p] --pattern <arg>
       [--postfix-separator <arg>] [-t <arg>] [-v <arg>]
 -a,--postfix <arg>             Appends postfix (after) to the latest
                                fetched or provided version. If not
                                provided, the program will try to reuse
                                existing postfix from the last tag.
                                Whenever the last tag contains number it's
                                incremented if and only if the provided
                                postfix matches with the tag's postfix.
                                Even if this last tag has no postfixes it
                                will be omitted. Increment is always
                                preferred on postfix (1.0.0-dev1 ->
                                1.0.0-dev2), however if no postfix is
                                found or provided the last number segment
                                from the tag is incremented. (1.0.0 ->
                                1.0.1)
    --everywhere                [DEPRECATED] Instructs the program to look
                                for tags in every branch. This might be
                                useful when features are separated by
                                branches and not bound to specific
                                version. If disabled the program will look
                                only into merged tags (ie. current
                                branch).
 -f,--force                     Forces all tags on all remotes (or push
                                targets) to be deleted and created again.
                                Tags will be deleted locally and on the
                                provided or all remotes. Please note that
                                not clearing tags on all remotes might
                                result in pull conflicts.
 -p,--push                      Informs the program that you want to also
                                push the tag you created. This is by
                                default to all remotes. You can limit
                                remotes by using -t or --push-targets.
    --pattern <arg>             Specifies java regex pattern for the
                                program to use when listing tags. It
                                bypasses git's implementation since it
                                uses non-robust wildcard implementation.
                                This parameter is required. See
                                documentation for examples, visit
                                regex101.com to test your implementation.
    --postfix-separator <arg>   Separator which will be used for parsing
                                appended postfix and appending the postfix
                                when constructing tag. Beware that if you
                                want to change separator, you need to
                                provide the entire ensemble. (ie. postfix,
                                postfix-separator and version)
 -t,--push-targets <arg>        Specifies which targets are used in order
                                to push the newly created tags. Targets
                                are separated with "," with no spaces.
                                When used -p and -t in conjunction, the
                                targets must not be empty.
 -v,--version <arg>             Program will use this version code without
                                any increments in order to construct new
                                tag. Note that if postfix was provided it
                                will use the postfix as well in the tag
                                construction process.
```

## How `taggy` works

It works as a wrapper over `git` client installed on your device to fetch, sort, create and publish tags with respect to
the semantic versioning scheme. It uses a consistent comparator to determine which tag is the newest.

---

The program will try to increment the lowest available version segment.

```bash
# <- 1.0.0
tagsem
# -> 1.0.1

# <- 1.0.0-alpha1 
tagsem
# -> 1.0.0-alpha2

# <- 1.0.0-alpha3
tagsem -a beta
# -> 1.0.1-beta1
```

---

You can use `taggy` to publish (push) tags on your behalf.

```bash
tagsem -p
```

And also implicitly request to rewrite any existing tags.

```bash
tagsem -fp
```

---

Completely new version? Not a problem.

```bash
tagsem -v 2.0.0 -a alpha1
```

Done with 2.0.0? Let's ship it!

```bash
tagsem -v 2.0.0 -p -t origin,eaplay
```

<a href="https://www.flaticon.com/free-icons/dog-tag" title="dog tag icons">Dog tag icons created by Nikita Golubev -
Flaticon</a>