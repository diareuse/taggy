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

## Patterns

As taggy aims to support different kinds of version schemes without the knowledge of the given scheme, you're required
to provide the utility with valid regex scheme. It will automatically filter the results and sort them for you.

The main requirement is only that the leftmost number has the highest priority and so the leftmost has the least.

```bash
alias tagsem="taggy --pattern \"([0-9]+\.)+[0-9]+(-[a-z]+[0-9]+)?\"" # 2.13.46 || 2.13.46.1 || 2.13.46-dev2
alias tagint="taggy --pattern \"$(date +%Y)(\.[0-9]+)+(-[a-z]+[0-9]+)?\"" # 2021.10 || 2021.10.1 || 2021.10-dev2
# add more aliases of your liking
```

## Basic Usage

### First tag

```bash
tagsem -v 1.0.0 # -> creates 1.0.0
```

### Basic increments

Given last tag is `2.0.10`

```bash
tagsem # -> creates 2.0.11
```

Given last tag is `2.0.11-dev1`

```bash
tagsem         # -> creates 2.0.11-dev2
tagsem -a test # -> creates 2.0.12-test1
tagsem -a test # -> creates 2.0.12-test2
tagsem -a dev  # -> creates 2.0.13-dev1
# and so forth…
```

### Starting new version

```bash
tagsem -v 1.1.0 -a dev # -> creates 1.1.0-dev1
```

### Change separator

Given last tag is `1.1.1`

```bash
tagsem -a test --postfix-separator="/" # -> creates 1.1.2/test1
```

### Push

Given last tag is `1.1.2-test1`

```bash
tagsem -p           # -> pushes 1.1.2-test2 to all remotes
tagsem -p -t origin # -> pushes 1.1.2-test3 to origin
```

### Conflicting tag

```bash
tagsem -v 1.1.2 -a test3 -p -f # -> deletes references to 1.1.2-test3, creates 1.1.2-test3 and pushes to all remotes
```

<a href="https://www.flaticon.com/free-icons/dog-tag" title="dog tag icons">Dog tag icons created by Nikita Golubev -
Flaticon</a>