(ns ^:figwheel-hooks scales2do.scales)

(def fifths
  "a vector of pairs: [major minor]"
  [["C"  "A"]
   ["G"  "E"]
   ["D"  "B"]
   ["A"  "F#"]
   ["E"  "C#"]
   ["B"  "G#"]
   ["F#" "E♭"]])

(def fourths
  "a vector of pairs: [major minor]"
  [["C"  "A"]
   ["F"  "D"]
   ["B♭" "G"]
   ["E♭" "C"]
   ["A♭" "F"]
   ["D♭" "B♭"]])

(defn major [vec]
  "given a vector of pairs, return a vector of major scales"
  (mapv first vec))

(defn minor [vec]
  "given a vector of pairs, return a vector of minor scales"
  (mapv second vec))

(defn fifths-major []
  (major fifths))

(defn fifths-minor []
  (minor fifths))

(defn fourths-major []
  (major fourths))

(defn fourths-minor []
  (minor fourths))
