# scales2do

![cycle of fifths, cycle of fourths](https://github.com/dhodges/scales2do/raw/master/resources/public/cycles.png)

Simple tool to help music practice.
Click the inner circle for a random choice of scale.

created with clj-new
```
$ clj -A:new figwheel-main scales2do/core -- --reagent
$ mv core scales2do
$ cd scales2do
```

## Development

To launch a repl from the shell, including auto-compile and load:

```
$ clojure -A:fig:build
```

To launch a cider cljs repl from emacs, run cider-jack-in-cljs (C-c M-J)

To clean all compiled files:

```
$ rm -rf target/public
```

To create a minimal build:

```
$ rm -rf target/public
$ clojure -A:fig:prod
```

## Testing

To run tests from the command line:

    clj -A:fig:test

Tests are auto-updated and run if you open a browser tab to:

    http://localhost:9500/figwheel-extra-main/auto-testing


## Deployment

To create a production build:

```
$ clojure -A:fig:prod
```

## License

Copyright © 2019 david hodges

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
