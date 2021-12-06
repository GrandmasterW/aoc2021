(ns aoc2021.day1
  (:require [clojure.string :as s]))

(def example-report
  [
   199
   200
   208
   210
   200
   207
   240
   269
   260
   263]
  )

(def day1-input  "src/aoc2021/day1-input.txt")

(defn get-day1-numbers [filename]
  (->> filename
       (slurp)
       (s/split-lines)
       (mapv #(Integer/parseInt %1))))


(defn count-inc
  "returns how many increased value differences are contained in v"
  [v]
  (let [v1 (into [] (cons (first v) v))]
    (count
     (filter true?
             (map > v v1)))))

(defn sum3
  "returns a vector of values of summarized groups by a floating window: 0 1 2, 1 2 3, 2 3 4, and so on"
  [v]
  (mapv + v (drop 1 v) (drop 2 v)))


(defn count-win3
  "returns the number of increases in value sets"
  [v]
  (count-inc (sum3 v)))
