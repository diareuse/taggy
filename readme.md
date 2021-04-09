# Taggy!

_Make your tagging easier and predictable_

## Patterns

As taggy aims to support different kinds of version schemes without the knowledge of the given scheme, you're required
to provide the utility with valid regex scheme. It will automatically filter the results and sort them for you.

The main requirement is only that the leftmost number has the highest priority and so the leftmost has the least.

```bash
alias tagsem="taggy --pattern \"[0-9]+.[0-9]+.[0-9]+\"" # 2.13.46
alias tagint="taggy --pattern \"$(date +%Y).[0-9]+\"" # 2021.10
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

### Push!

Given last tag is `1.1.2-test1`

```bash
tagsem -p           # -> pushes 1.1.2-test2 to all remotes
tagsem -p -t origin # -> pushes 1.1.2-test3 to origin
```

### Conflicting tag?

```bash
tagsem -v 1.1.2 -a test3 -p -f # -> deletes references to 1.1.2-test3, creates 1.1.2-test3 and pushes to all remotes
```
