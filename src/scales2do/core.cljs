(ns ^:figwheel-hooks scales2do.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [scales2do.behaviour :refer [setup-behaviours]]
   [scales2do.circle-component :as comp]))

(defn mount [component el]
  (reagent/render-component [component] el))

(defn mount-app-elements []
  (when-let [el (gdom/getElement "circle-svg")]
    (mount comp/circle-svg el)
    (setup-behaviours)))

(mount-app-elements)

(defn ^:after-load on-reload []
  (mount-app-elements))
