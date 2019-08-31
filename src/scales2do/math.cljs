(ns ^:figwheel-hooks scales2do.math
  (:require [clojure.string :as str]))

(defn rotate-pt-around-center
  [{:keys [x y angle cx cy]}]
  (let [angle  (* angle (/ Math.PI 180))  ;; convert to radians
        cos-angle (js/Math.cos angle)
        sin-angle (js/Math.sin angle)
        diff-x (- x cx)
        diff-y (- y cy)
        rotated-x (+ cx (- (* cos-angle diff-x)
                           (* sin-angle diff-y)))
        rotated-y (+ cy (+ (* sin-angle diff-x)
                           (* cos-angle diff-y)))]
    {:x (int rotated-x) :y (int rotated-y)}))

;; see: https://stackoverflow.com/posts/18473154/revisions

(defn polar2cartesian [cx cy radius angle]
  ;; first, convert angle to radians
  (let [angle (/ (* (- angle 90) js/Math.PI)
                 180.0)
        cos-angle (js/Math.cos angle)
        sin-angle (js/Math.sin angle)]
    {:x (int (+ cx (* radius cos-angle)))
     :y (int (+ cy (* radius sin-angle)))}))

(defn describe-arc [{:keys [cx cy radius start-angle end-angle clockwise]}]
  "describe an arc, suitable for use in an svg path,
given a center pt, radius, start and end angles in degrees,
and whether to render in a clockwise direction"
  (let [start (polar2cartesian cx cy radius end-angle)
        end   (polar2cartesian cx cy radius start-angle)
        large-arc-flag 0]
    ;; NB: we assume only small arc segments,
    ;; therefore large-arc-flag is always 0
    (str/join " "
              ["M" (:x start) (:y start)
               "A" radius radius 0 large-arc-flag
               (if clockwise 1 0)
               (:x end) (:y end)])))
