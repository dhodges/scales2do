(ns scales2do.behaviour
  (:require
   [scales2do.circle-component :refer [app-state scale2id]]
   [goog.dom :as gdom]))

(defn highlight-scale [scale-id]
  (when-not (= nil (:current-scale-id @app-state))
    (swap! app-state assoc :previous-scale-ids
           (conj (:previous-scale-ids @app-state)
                 (:current-scale-id @app-state))))
  (swap! app-state assoc :current-scale-id scale-id))

(defn previously-seen? [scale-id]
  (some #(= scale-id %) (:previous-scale-ids @app-state)))

 (defn random-scale-id []
     "return a random choice of scale as a tuple: [scale type] e.g. [\"C\" :major]"
     (let [type  (if (> (int (rand 2)) 0) :major :minor)
           ndx   (int (rand 12))
           key   (keyword (str (name type) "-scale-names"))
           scale (nth (get @app-state key) ndx)
           scale-id (scale2id scale type)]
       (if (previously-seen? scale-id)
         (random-scale-id)
         scale-id)))

(defn highlight-random-scale []
  (highlight-scale (random-scale-id)))

(defn choose-random-scale []
  (if (< (count (:previous-scale-ids @app-state)) 24)
    (highlight-random-scale)
    (when (js/confirm "All scales have been chosen. Start again?")
      (swap! app-state assoc :current-scale-id nil)
      (swap! app-state assoc :previous-scale-ids [])
      (highlight-random-scale))))

(defn setup-behaviours []
  (.addEventListener (gdom/getElement "circle-inner")
                     "click"
                     choose-random-scale))
