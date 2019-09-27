(ns scales2do.behaviour
  (:require
   [scales2do.circle-component :refer [app-state reset-scale-ids-to-show]]
   [scales2do.scales :refer [family-scales]]
   [goog.dom :as gdom]))

(defn highlight-cell [scale-id]
  (when-let [cell (.querySelector js/document ".cell.highlight")]
    (-> cell
        (.-classList)
        (.remove "highlight")))
  (when-let [elem (.getElementById js/document scale-id)]
    (-> elem
        (.-classList)
        (.add "highlight"))))

(defn highlight-next-scale []
  (when (= 0 (count (:scale-ids-to-show @app-state)))
    (reset-scale-ids-to-show))

  (let [scale-id      (first (:scale-ids-to-show @app-state))
        ids-remaining (rest  (:scale-ids-to-show @app-state))]
    (swap! app-state assoc :current-scale-id scale-id)
    (swap! app-state assoc :scale-ids-to-show ids-remaining)
    (highlight-cell scale-id)))

(defn next-scale-id []
  (when (= 0 (count (:scale-ids-to-show @app-state)))
    (reset-scale-ids-to-show))
  (first (:scale-ids-to-show @app-state)))

(defn recycle-seq [item seq]
  "generate a new sequence in the same order, beginning with the given item"
  (let [ndx (.indexOf (to-array seq) item)
        [head tail] (split-at (inc ndx) seq)]
    (concat tail head)))

(defn spin-highlights [ids]
  (.setTimeout js/window (fn []
                           (if (< 0 (count ids))
                             (do
                               (highlight-cell  (first ids))
                               (spin-highlights (rest ids)))
                             (highlight-next-scale)))
               70))

(defn show-spinning-scales []
  (let [current-id     (:current-scale-id @app-state)
        current-scales (family-scales current-id)
        current-ndx    (.indexOf (to-array current-scales) current-id)
        ids            (recycle-seq current-id current-scales)
        next-id        (next-scale-id)
        next-scales    (family-scales next-id)
        next-scales    (apply concat (reverse (split-at current-ndx next-scales)))
        next-ndx       (.indexOf (to-array next-scales) next-id)
        next-scales    (first (split-at next-ndx next-scales))
        ids            (dedupe (concat ids next-scales))]
    (swap! app-state assoc :current-scale-id "")
    (spin-highlights ids)))

(defn setup-behaviours []
  (reset-scale-ids-to-show)
  (.addEventListener (gdom/getElement "circle-inner")
                     "click"
                     show-spinning-scales)
  (highlight-next-scale))
