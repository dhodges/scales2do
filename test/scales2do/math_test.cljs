(ns scales2do.math-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [scales2do.math :refer [rotate-pt-around-center]]))

;; helper
(defn rotate [{:as args}]
  (rotate-pt-around-center (merge args {:cx 200 :cy 200})))

(deftest rotate-x-y-around-center-test
  (is (= {:x 200 :y   0} (rotate {:x   0 :y 200 :angle 90})))
  (is (= {:x 400 :y 200} (rotate {:x 200 :y   0 :angle 90})))
  (is (= {:x 200 :y 400} (rotate {:x 400 :y 200 :angle 90})))

  (is (= {:x 148 :y   6} (rotate {:x 200 :y   0 :angle -15})))
  (is (= {:x 251 :y   6} (rotate {:x 200 :y   0 :angle  15})))
  (is (= {:x 482 :y 200} (rotate {:x 400 :y 400 :angle -45})))
)
