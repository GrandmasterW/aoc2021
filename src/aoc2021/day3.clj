(ns aoc2021.day3
  (:require [clojure.string :as s]
            [aoc2021.load :as ld]
            [aoc2021.util :as ut]))

(def day3-example-input "src/aoc2021/day3-example-input.txt")
(def day3-full-input "src/aoc2021/day3-input.txt")

;; example

(def m1 [[0 1 0 1 0]
         [0 1 0 1 0]
         [1 0 0 1 0]])

(defn get-day3-instructions
  "returns a vector of one vector for each line, loaded from filename" 
  [filename]
  (->> filename
       (ld/load-lines-to-vec)
       (into [])))

;; ----------------------------------------------------------------------
;; gamma and epsilon
;; ----------------------------------------------------------------------

(defn common-count
  "Returns the gamma values for each column as mostly used value. Takes a vector of single vectors, each containing 5 digits" 
  [v pred]
   (mapv
    (comp first (partial reduce pred))
    (mapv frequencies (ut/transpose v))))

(defn gamma
  [v]
  (common-count v ut/max-freq))

(defn epsilon
  ""
  [v]
  (common-count v ut/min-freq))
  

;; ------------------------------------------------------------
;; result computation
;; ----------------------------------------------------------------------

(defn day3a-result
  "a * b"
  [a b ]
  (* a b))

