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

(defn make-text [x y cx cy angle class [ndx scale]]
  (let [{:keys [x y]} (rotate-pt-around-center
                       {:x x :y y :cx cx :cy cy :angle (* ndx angle)})
        id (scale2id scale class)
        class (if (highlight-scale? id) (str class " highlight") class)]
    [:text {:x x :y y :class class :id id :key id} scale]))

(defn indexed-names [seq]
  "return a sequence of pairs: [[item-index item]...]"
  (map-indexed (fn [ndx item] [ndx item]) seq))

(defn segment-lines []
  [:g
   [:line#line {:x1 200 :y1 200 :x2 148 :y2 15 :stroke-width 1}]
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
   [:path#major-path {:d (describe-arc {:cx 200 :cy 200 :radius 132 :start-angle -10 :end-angle 12 :clockwise true})
                      :fill "transparent" :stroke "none"}]
   [:text {:class "label"}
    [:textPath {:xlinkHref "#major-path" :text-anchor "start"} "Major"]]

   [:path#minor-path {:d (describe-arc {:cx 200 :cy 200 :radius 125 :start-angle -168 :end-angle -192 :clockwise false})
                      :fill "transparent" :stroke "none"}]
   [:text {:class "label"}
    [:textPath {:xlinkHref "#minor-path" :text-anchor "start"} "Minor"]]])

(defn major-scales []
  [:g
   (doall (map (partial make-text 200 40 200 200 30 "major")
               (indexed-names (:major-scale-names @app-state))))])

(defn minor-scales []
  [:g
   (doall (map (partial make-text 200 105 200 200 30 "minor")
               (indexed-names (:minor-scale-names @app-state))))])

;; the main "circle of cycles" svg component

(defn circle-svg []
  [:svg {:width 400 :height 400}
   [:g {:stroke "gray" :fill "white"}
    [:circle#circle-outer  {:cx 200 :cy 200 :r 190 :stroke-width 3}]
    [:circle#circle-middle {:cx 200 :cy 200 :r 130 :stroke-width 2}]
    [segment-lines]
    [:circle#circle-inner {:cx 200 :cy 200 :r  70 :stroke-width 2}]
    [major-minor-labels]
    [:g {:text-anchor "middle" :dominant-baseline "middle" :fill "black"}
     [major-scales]
     [minor-scales]]]])
