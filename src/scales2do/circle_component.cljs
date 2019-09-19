(ns ^:figwheel-hooks scales2do.circle-component
  (:require
   [clojure.string :as str]
   [scales2do.geometry :refer [describe-arc rotate-pt-around-center]]
   [scales2do.scales :as scales]
   [reagent.core :as reagent :refer [atom]]))

(defonce app-state
  (atom
   {;; outer circle of scale names
    :major-scale-names (concat (scales/fifths-major)
                               (reverse (rest (scales/fourths-major))))
    ;; inner circle of scale names
    :minor-scale-names (concat (scales/fifths-minor)
                               (reverse (rest (scales/fourths-minor))))
    :current-scale-id nil
    :previous-scale-ids []}))

(defn scale2id [scale type]
  "return id an for the dom, given a name and a type (i.e. :major or :minor)"
  (str (if (str/ends-with? scale "#")
         (str (first scale) "-sharp")
         (if (str/ends-with? scale "♭")
           (str (first scale) "-flat")
           scale))
       "-" (name type))) ;; convert type from keyword

(defn highlight-scale? [scale-id]
  (= scale-id (:current-scale-id @app-state)))

(defn make-text [x y angle class [ndx scale]]
  (let [{:keys [x y]} (rotate-pt-around-center
                       {:x x :y y :angle (* ndx angle)})
        id (scale2id scale class)
        class (if (highlight-scale? id) (str class " highlight") class)]
    [:text {:x x :y y :class class :id id :key id} scale]))

(defn indexed-names [seq]
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

(defn major-scales []
  [:g
   (doall (map (partial make-text 0 -160 30 "major")
               (indexed-names (:major-scale-names @app-state))))])

(defn minor-scales []
  [:g
   (doall (map (partial make-text 0 -100 30 "minor")
               (indexed-names (:minor-scale-names @app-state))))])

(defn major-minor-scales []
  [:g {:text-anchor "middle" :dominant-baseline "middle" :fill "black"}
   [major-scales]
   [minor-scales]])

(defn scale-names []
  [:g
   [major-minor-labels]
   [major-minor-scales]])
