(ns aoc2021.day5
  (:require [clojure.string :as str]
            [aoc2021.load :as ld]
            [aoc2021.util :as ut]))


;; vents


(def example-input "src/aoc2021/day5-example-input.txt")
(def full-input "src/aoc2021/day5-full-input.txt")


;; ----------------------------------------------------------------------
;; loading data into structures
;; ----------------------------------------------------------------------

(defn str-to-point
  "converts a string '1,3' into a vector [1 2]"
  [s]
  (mapv ut/parse-int
        (str/split (str/trim s) #",")))

(defn line-coords-v
  "convert a string consisting of 'a,b->c,d' into a vector [a b][c d]"
  [line]
  (mapv str-to-point
        (str/split line #"->")))

(defn get-input
  "load line coordinates"
  [filename]
  (mapv line-coords-v (ld/load-lines-to-vec filename)))

(defn is-ortho?
  "true for lines coordinates where x1=x2 or y1=y2"
  [[[x1 y1][x2 y2]]]
  (or (= x1 x2)
      (= y1 y2)))



;; ----------------------------------------------------------------------
;; create diagram from line coordinates
;; ----------------------------------------------------------------------

(defn xyrange
  "returns a repeat function, if m and n are equal, range min max otherwise"
  [m n]
  (cond (= m n) (repeat m)
        (> m n) (range m (dec n) -1)
        :else (range m (inc n))))

(defn line-points
  "returns the points of a line for a given pair of coordinates orthogonal line as a vector of points: [[1 2] [2 2]]"
  [[[x1 y1][x2 y2]]]
  (mapv vector (xyrange x1 x2)(xyrange y1 y2)))

(defn overlaps
  "returns the points where lines intersect, i.e. more than one line. Expects a single sequence of points [a b]!"
  [points]
  (->> points
       (frequencies)
       (filter #(> (second %) 1))
       (mapv #(first %))))

;; ----------------------------------------------------------------------
;; results
;; ----------------------------------------------------------------------

(defn day5a-result
  "Hydrothermal Venture, part 1"
  [f]
  (->> f
       (get-input) ; convert into coords
       (filterv is-ortho?) ; use only orthogonal lines
       (mapv line-points) ; vec of one vector of points per line
       (mapcat identity) ; convert into single vector of points
       (overlaps) ; find overlapping line points
       (count) 
  ))
  
(defn day5b-result
  "Hydrothermal Venture, part 2 with diagonal lines"
  [f]
  (->> f
       (get-input) ; convert into coords
       (mapv line-points) ; vec of one vector of points per line
       (mapcat identity) ; convert into single vector of points
       (overlaps) ; find overlapping line points
       (count) 
  ))
 



