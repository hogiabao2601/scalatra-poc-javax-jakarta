# My Scalatra Web App #

## Build & Run ##

```sh
$ cd my-scalatra-web-app
$ sbt
> jetty:start
> browse
```

If `browse` doesn't launch your browser, manually open [http://localhost:8080/](http://localhost:8080/) in your browser.

How to build both javax and jakarta projects:
```sh
$ cd my-scalatra-web-app
$ sbt -Denv=javax "project javaxProject" clean package 
$ sbt -Denv=jakarta "project jakartaProject" clean package
```
