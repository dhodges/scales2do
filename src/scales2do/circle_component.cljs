(ns ^:figwheel-hooks scales2do.circle-component
  (:require
   [clojure.string :as str]
   [scales2do.geometry :refer [describe-arc rotate-pt-around-center]]
   [scales2do.scales :as scales]
   [reagent.core :as reagent :refer [atom]]))

(defonce app-state
  (atom
   {
    :current-scale-id nil
    :scale-ids-to-show []}))

(defn reset-scale-ids-to-show []
  (swap! app-state assoc :scale-ids-to-show
         (shuffle scales/all-scale-ids)))

(defn highlight-scale? [scale-id]
  (= scale-id (:current-scale-id @app-state)))

(defn make-scale-element [x y angle class [ndx [scale-id scale-label]]]
  (let [{:keys [x y]} (rotate-pt-around-center
                       {:x x :y y :angle (* ndx angle)})
        class (if (highlight-scale? scale-id) (str class " highlight") class)]
    [:text {:x x :y y :class class :id scale-id :key scale-id} scale-label]))

(defn indexed-items [seq]
  "return a sequence of pairs: [[item-index item]...]"
  (map-indexed (fn [ndx item] [ndx item]) seq))

(defn major-minor-labels []
  "curved labels 'Major' and 'Minor', marking the outer and inner circles"
  [:g
   (let [attrs-major {:x 0 :y -200 :text-anchor "middle" :class "label"}]
     [:g {:transform "scale(0.68)"}
      [:text (merge attrs-major {:transform "rotate(-60)"}) "M"]
      [:text (merge attrs-major {:transform "rotate(-30)"}) "A"]
      [:text (merge attrs-major {:transform "rotate(  0)"}) "J"]
      [:text (merge attrs-major {:transform "rotate( 30)"}) "O"]
      [:text (merge attrs-major {:transform "rotate( 60)"}) "R"]])

   (let [attrs-minor {:x 0 :y 200 :text-anchor "middle" :class "label"}]
     [:g {:transform "scale(0.63)"}
      [:text (merge attrs-minor {:transform "rotate( 60)"}) "M"]
      [:text (merge attrs-minor {:transform "rotate( 30)"}) "I"]
      [:text (merge attrs-minor {:transform "rotate(  0)"}) "N"]
      [:text (merge attrs-minor {:transform "rotate(-30)"}) "O"]
      [:text (merge attrs-minor {:transform "rotate(-60)"}) "R"]])

   ])

(defn major-scale-names []
  [:g
   (doall (map (partial make-scale-element 0 -160 30 "major")
               (indexed-items scales/major-scales)))])

(defn minor-scale-names []
  [:g
   (doall (map (partial make-scale-element 0 -100 30 "minor")
               (indexed-items scales/minor-scales)))])

(defn all-scale-names []
  [:g {:text-anchor "middle" :dominant-baseline "middle" :fill "black"}
   [major-scale-names]
   [minor-scale-names]])

(defn scale-names []
  [:g
   [major-minor-labels]
   [all-scale-names]])
