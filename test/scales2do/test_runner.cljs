;; This test runner is intended to be run from the command line
(ns scales2do.test-runner
  (:require
   ;; require all the namespaces that you want to test
   [scales2do.circle-component-test]
   [scales2do.core-test]
   [scales2do.geometry-test]
   [figwheel.main.testing :refer [run-tests-async]]))

(defn -main [& args]
  (run-tests-async 5000))
