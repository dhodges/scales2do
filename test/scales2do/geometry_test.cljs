(ns scales2do.geometry-test
    (:require
     [cljs.test :refer-macros [deftest is testing]]
     [scales2do.geometry :refer [rotate-pt-around-center
                                 polar2cartesian]]))

(deftest rotate-x-y-around-center-test
  (is (= {:x -200 :y   0} (rotate-pt-around-center {:x   0 :y 200 :angle 90})))
  (is (= {:x    0 :y 200} (rotate-pt-around-center {:x 200 :y   0 :angle 90})))
  (is (= {:x -199 :y 400} (rotate-pt-around-center {:x 400 :y 200 :angle 90})))

  (is (= {:x 193 :y -51} (rotate-pt-around-center {:x 200 :y   0 :angle -15})))
  (is (= {:x 193 :y  51} (rotate-pt-around-center {:x 200 :y   0 :angle  15})))
  (is (= {:x 193 :y -65} (rotate-pt-around-center {:x 200 :y  40 :angle -30})))
  (is (= {:x 223 :y -13} (rotate-pt-around-center {:x 200 :y 100 :angle -30})))
  (is (= {:x 565 :y   0} (rotate-pt-around-center {:x 400 :y 400 :angle -45}))))

(deftest polar2cartesian-test
  (is (= {:x -7 :y -28} (polar2cartesian {:radius  30 :angle -15})))
  (is (= {:x 49 :y -49} (polar2cartesian {:radius  70 :angle  45})))
  (is (= {:x 0  :y 110} (polar2cartesian {:radius 110 :angle 180}))))
