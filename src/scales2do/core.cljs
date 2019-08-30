(ns ^:figwheel-hooks scales2do.core
  (:require
   [reagent.core :as reagent :refer [atom]]
   [scales2do.circle-component :as comp]
   [scales2do.dom :as dom]))

(defn mount [component el]
  (reagent/render-component [component] el))

(defn mount-app-elements []
  (when-let [el (dom/select-element "#circle-svg")]
    (mount comp/circle-svg el)))

(mount-app-elements)

(defn ^:after-load on-reload []
  (mount-app-elements))
