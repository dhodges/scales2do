(ns scales2do.behaviour-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [scales2do.behaviour :refer [recycle-seq]]))

(deftest recycle-seq-test
  (is (= ["D" "E" "F" "A" "B" "C"] (recycle-seq "C" ["A" "B" "C" "D" "E" "F"]))))
