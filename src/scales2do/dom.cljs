(ns ^:figwheel-hooks scales2do.dom)

(defn bounding-rect [elem]
  (let [winX   (. js/window -scrollX)
        winY   (. js/window -scrollY)
        rect   (. elem getBoundingClientRect)
        left   (+ (. rect -left)   winX)
        top    (+ (. rect -top)    winY)
        right  (+ (. rect -right)  winX)
        bottom (+ (. rect -bottom) winY)
        width  (.-clientWidth elem)
        height (.-clientHeight elem)
        center {:x (int (+ left (/ width 2)))
                :y (int (+ top  (/ height 2)))}]
    {:left   left
     :top    top
     :right  right
     :bottom bottom
     :width  width
     :height height
     :center center}))

(defn select-element [selector]
  (js/document.querySelector selector))
