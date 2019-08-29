# scales2do

created with clj-new
```
$ clj -A:new figwheel-main scales2do/core -- --reagent
$ mv core scales2do
$ cd scales2do
```

## Development

To launch a repl from the shell, including auto-compile and load:

```
$ clojure -A:fig:build  # An easy way to test: (js/alert "Am I connected?")
```


To clean all compiled files:

```
$ rm -rf target/public
```

To create a production build:

```
$ rm -rf target/public
$ clojure -A:fig:min
```

To launch a cider cljs repl from emacs, run cider-jack-in-cljs (C-c M-J)

## Testing

To run tests from the command line:

    clj -A:fig:test

Tests are auto-updated and run if you open a browser tab to:

    http://localhost:9500/figwheel-extra-main/auto-testing


## License

Copyright © 2019 david hodges

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
