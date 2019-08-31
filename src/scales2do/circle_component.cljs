(ns ^:figwheel-hooks scales2do.circle-component
  (:require
   [scales2do.scales :as scales]
   [scales2do.math :refer [rotate-pt-around-center describe-arc]]
   [reagent.core :as reagent :refer [atom]]))

(defonce app-state
  (atom
   {:scales
    {:fifths  {:major (scales/fifths-major)
               :minor (scales/fifths-minor)}
     :fourths {:major (scales/fourths-major)
               :minor (scales/fourths-minor)}}}))

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

(defn circle-svg []
  [:svg {:width 400 :height 400}
   [:g {:stroke "gray" :fill "white"}
    [:circle {:id "circle-outer"  :cx 200 :cy 200 :r 190 :stroke-width 4}]
    [:circle {:id "circle-middle" :cx 200 :cy 200 :r 130 :stroke-width 2}]
    [segment-lines]
    [:circle {:id "circle-inner"  :cx 200 :cy 200 :r  70 :stroke-width 2}]
    [:g {:text-anchor "middle" :dominant-baseline "middle" :fill "black" :font-family "Arial, Helvetica, sans-serif"}
     [:g {:font-size 32}
      (let [major-fifths  (get-in @app-state [:scales :fifths :major])
            major-fourths (rest (get-in @app-state [:scales :fourths :major]))]

        (concat
         (map (partial make-text 200 40 200 200 30 "major")
              (indexed-names major-fifths))
         (map (partial make-text 120 61 200 200 -30 "major")
              (indexed-names major-fourths))))]

     [:g {:font-size 24}
      (let [minor-fifths  (get-in @app-state [:scales :fifths :minor])
            minor-fourths (rest (get-in @app-state [:scales :fourths :minor]))]

        (concat
         (map (partial make-text 200 100 200 200 30 "minor")
              (indexed-names minor-fifths))
         (map (partial make-text 150 113 200 200 -30 "minor")
              (indexed-names minor-fourths))))]]]])
