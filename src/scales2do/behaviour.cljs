(ns scales2do.behaviour
  (:require
   [scales2do.circle-component :refer [app-state reset-scale-ids-to-show scale2id]]
   [goog.dom :as gdom]))

(defn highlight-random-scale []
  (let [scale-ids-to-show (:scale-ids-to-show @app-state)
        scale-id          (first scale-ids-to-show)]
    (swap! app-state assoc :scale-ids-to-show (rest scale-ids-to-show))
    (swap! app-state assoc :current-scale-id scale-id)))

(defn choose-random-scale []
  (if (< 0 (count (:scale-ids-to-show @app-state)))
    (highlight-random-scale)
    (when (js/confirm "All scales have been chosen. Start again?")
      (swap! app-state assoc :current-scale-id nil)
      (reset-scale-ids-to-show)
      (highlight-random-scale))))

(defn setup-behaviours []
  (reset-scale-ids-to-show)
  (.addEventListener (gdom/getElement "circle-overlay")
                     "click"
                     choose-random-scale)
  (choose-random-scale))