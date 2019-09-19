(ns ^:figwheel-hooks scales2do.geometry
  (:require [clojure.string :as str]))

(defn rotate-pt-around-center
  "rotate the given point around 0,0"
  [{:keys [x y angle]}]
  (let [cx 0
        cy 0
        angle  (* angle (/ Math.PI 180))  ;; convert to radians
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

(defn polar2cartesian [{:keys [radius angle]}]
  "with origin at 0,0 convert polar coordinates to cartesian"
  (let [cx 0
        cy 0
        ;; convert angle to radians
        angle (/ (* (- angle 90) js/Math.PI)
                 180.0)
        cos-angle (js/Math.cos angle)
        sin-angle (js/Math.sin angle)]
    {:x (int (+ cx (* radius cos-angle)))
     :y (int (+ cy (* radius sin-angle)))}))

(defn describe-arc [{:keys [radius start-angle end-angle clockwise]}]
  "describe an arc, suitable for use in an svg path,
with origin at 0,0 and the given radius, start and end angles in degrees,
and whether to render in a clockwise direction"
  (let [cx 0
        cy 0
        start (polar2cartesian {:radius radius :angle start-angle})
        end   (polar2cartesian {:radius radius :angle end-angle})
        ;; NB: we assume only small arc segments,
        ;; therefore large-arc-flag is always 0
        large-arc-flag 0]
    (str/join " "
              ["M" (:x start) (:y start)
               "A" radius radius 0 large-arc-flag
               (if clockwise 1 0)
               (:x end) (:y end)])))
