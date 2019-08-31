(ns scales2do.circle-component-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [scales2do.circle-component :refer [name2id]]))

(deftest name2id-test
  (is (= "A-sharp" (name2id "A#")))
  )
