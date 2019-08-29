(ns ^:figwheel-hooks scales2do.math)

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
