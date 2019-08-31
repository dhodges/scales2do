(ns scales2do.circle-component-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [scales2do.circle-component :refer [scale2id]]))

(deftest scale2id-test
  (is (= "A-sharp-major" (scale2id "A#" "major")))
  (is (= "E-flat-minor"  (scale2id "E♭" "minor"))))
