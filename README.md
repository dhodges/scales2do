# scales2do

![cycle of fifths, cycle of fourths](https://github.com/dhodges/scales2do/raw/master/resources/public/img/cycles.png)

Simple tool for music practice. Click the inner circle for a random choice of scale.

Implemented as a progressive web app.

### Development

created with clj-new
```
$ clj -A:new figwheel-main scales2do/core -- --reagent
$ mv core scales2do
$ cd scales2do
```

To launch a cider cljs repl from emacs, run cider-jack-in-cljs `(C-c M-J)`.
Then to watch and auto-run tests, open: http://localhost:9500/figwheel-extra-main/auto-testing

Other tasks:

```
$ make clean     # clean all compiled files
$ make dev       # launch a repl from the shell, including auto-compile and load
$ make test      # run tests from the command line
$ make prod      # create a production build
```

### Progressive Web App

Includes a minimal nginx config to serve this PWA app over HTTPS. Obviously requires nginx (and openssl) to be installed.

```
$ make nginx_start     # ...then open https://localhost:4443
$ make nginx_stop      # to quit
```

Normally, Google Chrome will not accept self-signed certificates over HTTPS. We can get around this by launching Chrome with a few cmd-line switches:

```
$ make chrome_insecure # tell chrome to accept self-signed certificates from localhost
```

### Mea culpa

Some ideas and code respectfully stolen from: https://github.com/gja/pwa-clojure

Copyright © 2019 david hodges

Distributed under the Eclipse Public License either version 1.0 or (at your option) any later version.
