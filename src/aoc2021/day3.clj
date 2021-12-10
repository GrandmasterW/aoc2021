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
       (mapv ut/binstr-to-vec)))

;; ----------------------------------------------------------------------
;; binvec stuff
;; ----------------------------------------------------------------------
(defn digit-match?
  "true, if vector like [0 1 1] matches digit d at position p"
  [p d v]
  (= (nth v p) d))

(defn first-match
  "Returns the first matching row in a vector with these conditions: 
  1. row must have the digit at pos which matches the gamma or epsilon result at that pos
  2. left to right
  Example: \n
  m: [[0 0 0][0 0 1][0 1 0][0 1 1][1 0 0]]  
  result: [0 1 1]
  uses gamma or epsilon as first argument to compute relevant binaries"
  ([gefn m]
   (first-match gefn m 0))
  ([gefn m pos]
   ;(println "first-match\t" pos "\tm: " m)
   (if (or (nil? m)(>= pos (count (first m)))) [])
   (let [v (gefn m) ; redo computation on reduced m
         digit (nth v pos) 
         r (filter (partial digit-match? pos digit) m)]
    (if (= (count r) 1) (first r)
        (first-match gefn r (inc pos))))))


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
  
;; ----------------------------------------------------------------------
;; oxygen and co2 ratings
;; ----------------------------------------------------------------------

(def oxygen
  ; "Returns the oxygen report as binary vector for v"
  (partial first-match gamma))

(def co2
  ; "Returns the CO2 scrubber report as binary vector for v"
  (partial first-match epsilon))
  
;; ------------------------------------------------------------
;; result computation
;; ----------------------------------------------------------------------

                

(defn day3a-result
  "gimme the matrix with integers... I'll return the number"
  [m]
  (let [ga (ut/binvec-to-int (gamma m))
        ep (ut/binvec-to-int (epsilon m))]
  (* ga ep)))

(defn day3b-result
  "gimme the matrix with integers... I'll return the final number"
  [m]
  (let [ox (ut/binvec-to-int (oxygen m))
        co (ut/binvec-to-int (co2 m))]
  (* ox co)))

