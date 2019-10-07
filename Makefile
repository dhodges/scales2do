clean:
	rm -rf resources/public/js
	rm -f resources/public/service_worker.js

dev: clean
	clojure -A:fig:dev

service_worker:
	# figwheel code splitting might be an alternative to running multiple clj aliases:
	# https://figwheel.org/docs/code_splitting.html
	# see also:
	# https://github.com/bhauman/lein-figwheel/wiki/Using-Figwheel-with-Web-Workers
	clojure -A:service_worker

prod_compile:
	clojure -A:fig:prod

prod: | clean prod_compile service_worker

test: clean
	clj -A:fig:test

.PHONY:
	clean dev

certs:
	# NB: self-signed certificates can only be a temp solution


	#
	mkdir -p nginx/ssl
	sudo openssl req -x509 -nodes -days 365 -newkey rsa:2048 -keyout nginx/ssl/cert.key -out nginx/ssl/cert.crt

chrome_insecure:
	# this tells Chrome to allow self-signed certificates from localhost
	# i.e. so we can serve https locally in dev
	#
	/Applications/Google\ Chrome.app/Contents/MacOS/./Google\ Chrome  --ignore-certificate-errors --unsafely-treat-insecure-origin-as-secure=https://localhost

nginx_start:
	nginx -p `pwd`/nginx -c nginx.conf

nginx_stop:
	nginx -s quit
