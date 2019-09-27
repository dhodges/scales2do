clean:
	rm -rf resources/public/js

dev: clean
	clojure -A:fig:dev

prod: clean
	clojure -A:fig:prod

test: clean
	clj -A:fig:test

.PHONY:
	clean dev
