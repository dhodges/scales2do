# scales2do

![cycle of fifths, cycle of fourths](https://github.com/dhodges/scales2do/raw/master/resources/public/img/cycles.png)

Simple tool to help music practice. Click the inner circle for a random choice of scale.

created with clj-new
```
$ clj -A:new figwheel-main scales2do/core -- --reagent
$ mv core scales2do
$ cd scales2do
```

To launch a cider cljs repl from emacs, run cider-jack-in-cljs `(C-c M-J)`

To watch and auto-run tests, open a browser tab: http://localhost:9500/figwheel-extra-main/auto-testing

Other tasks:

```
$ make clean     # clean all compiled files
$ make dev       # launch a repl from the shell, including auto-compile and load
$ make test      # run tests from the command line
$ make prod      # create a production build
```

## License

Copyright © 2019 david hodges

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
