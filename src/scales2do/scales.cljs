(ns ^:figwheel-hooks scales2do.scales
  (:require [clojure.string :as str]))

(def major-scales
  "a vector of pairs: [element-id label]"
  [["C-major"       "C"]
   ["G-major"       "G"]
   ["D-major"       "D"]
   ["A-major"       "A"]
   ["E-major"       "E"]
   ["B-major"       "B"]
   ["F-sharp-major" "F#"]
   ["D-flat-major"  "D♭"]
   ["A-flat-major"  "A♭"]
   ["E-flat-major"  "E♭"]
   ["B-flat-major"  "B♭"]
   ["F-major"       "F"]])

(def minor-scales
  "a vector of pairs: [element-id label]"
  [["A-minor"       "A"]
   ["E-minor"       "E"]
   ["B-minor"       "B"]
   ["F-sharp-minor" "F#"]
   ["C-sharp-minor" "C#"]
   ["G-sharp-minor" "G#"]
   ["E-flat-minor"  "E♭"]
   ["B-flat-minor"  "B♭"]
   ["F-minor"       "F"]
   ["C-minor"       "C"]
   ["G-minor"       "G"]
   ["D-minor"       "D"]])

(def major-scale-names
  (map second major-scales))

(def major-scale-ids
  (map first major-scales))

(def minor-scale-names
  (map second minor-scales))

(def minor-scale-ids
  (map first minor-scales))

(def all-scale-ids
  (concat major-scale-ids minor-scale-ids))

(defn family? [scale-id]
  "to which family does the given scale belong?"
  (if (nil? scale-id)
    nil
    (if (str/ends-with? scale-id "major")
      "major"
      "minor")))

(defn family-scales [scale-id]
  (if (= (family? scale-id) "major")
    major-scale-ids
    minor-scale-ids))
