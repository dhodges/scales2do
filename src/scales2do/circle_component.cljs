(ns ^:figwheel-hooks scales2do.circle-component
  (:require
   [scales2do.scales :as scales]
   [scales2do.geometry :refer [rotate-pt-around-center describe-arc]]
   [reagent.core :as reagent :refer [atom]]))

(defonce app-state
  (atom
   {:scales {;; outer circle of scale names
             :major (concat (scales/fifths-major)
                            (reverse (rest (scales/fourths-major))))
             ;; inner circle of scale names
             :minor (concat (scales/fifths-minor)
                            (reverse (rest (scales/fourths-minor))))}}))

(defn make-text [x y cx cy angle class [ndx name]]
  (let [{:keys [x y]} (rotate-pt-around-center
                       {:x x :y y :cx cx :cy cy :angle (* ndx angle)})]
    [:text {:x x :y y :class class :id name :key name} name]))

(defn indexed-names [seq]
  "return a sequence of pairs: [[item-index item]...]"
  (map-indexed (fn [ndx item] [ndx item]) seq))

(defn segment-lines []
  [:g
   [:line {:id "line" :x1 200 :y1 200 :x2 148 :y2 15 :stroke-width 1}]
   [:use {:xlinkHref "#line" :transform "rotate( 30,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate( 60,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate( 90,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(120,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(150,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(180,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(210,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(240,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(270,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(300,200,200)"}]
   [:use {:xlinkHref "#line" :transform "rotate(330,200,200)"}]])

(defn major-minor-labels []
  "curved labels 'Major' and 'Minor', marking the outer and inner circles"
  [:g {:dominant-baseline "baseline" :stroke "gray" :fill "gray"}
   [:path {:id "major-path"
           :d (describe-arc {:cx 200 :cy 200 :radius 132 :start-angle 12 :end-angle -10 :clockwise true})
           :fill "transparent" :stroke "none"}]
   [:text {:class "label"}
    [:textPath {:xlinkHref "#major-path" :text-anchor "start"} "Major"]]

   [:path {:id "minor-path"
           :d (describe-arc {:cx 200 :cy 200 :radius 125 :start-angle -192 :end-angle -168 :clockwise false})
           :fill "transparent" :stroke "none"}]
   [:text {:class "label"}
    [:textPath {:xlinkHref "#minor-path" :text-anchor "start"} "Minor"]]])

(defn major-scales []
  [:g
   (map (partial make-text 200 40 200 200 30 "major")
        (indexed-names (get-in @app-state [:scales :major])))])

(defn minor-scales []
  [:g
   (map (partial make-text 200 105 200 200 30 "minor")
        (indexed-names (get-in @app-state [:scales :minor])))])

;; the main "circle of cycles" svg component

(defn circle-svg []
  [:svg {:width 400 :height 400}
   [:g {:stroke "gray" :fill "white"}
    [:circle {:id "circle-outer"  :cx 200 :cy 200 :r 190 :stroke-width 3}]
    [:circle {:id "circle-middle" :cx 200 :cy 200 :r 130 :stroke-width 2}]
    [segment-lines]
    [:circle {:id "circle-inner"  :cx 200 :cy 200 :r  70 :stroke-width 2}]
    [major-minor-labels]
    [:g {:text-anchor "middle" :dominant-baseline "middle" :fill "black"}
     [major-scales]
     [minor-scales]]]])
