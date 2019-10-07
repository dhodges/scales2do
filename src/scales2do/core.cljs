(ns ^:figwheel-hooks scales2do.core
  (:require
   [goog.dom :as gdom]
   [reagent.core :as reagent :refer [atom]]
   [scales2do.behaviour :refer [setup-behaviours]]
   [scales2do.circle-component :as comp]))

(defn mount [component el]
  (reagent/render-component [component] el))

(defn mount-app-elements []
  (when-let [el (gdom/getElement "scale-names")]
    (mount comp/scale-names el)
    (setup-behaviours)))

(defn ^:after-load on-reload []
  (mount-app-elements))

(defn make-progressive! []
  (when js/navigator.serviceWorker
    (js/console.log "[ServiceWorker] registering...")
    (-> (.register js/navigator.serviceWorker "/service_worker.js")
        (.then (fn [registration]
                 (js/console.log "[ServiceWorker] registered")))
        (.catch (fn [err]
                  (js/console.error #js{:code (.-code err)
                                        :name (.-name err)
                                        :message (.-message err)}))))))

(defn ^:export init []
  (mount-app-elements)
  (make-progressive!))
