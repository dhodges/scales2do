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
