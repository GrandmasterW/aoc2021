(ns aoc2021.load
  (:require [clojure.string :as s]))

;;(def day1-input  "src/aoc2021/day1-input.txt")

(defn load-lines-to-vec
  "returns a vector of strings for a file, consisting of one string per line"
  [filename]
  (->> filename
       (slurp)
       (s/split-lines)
       (into [])))

(defn load-lines-to-ints
  "Returns a vector of ints, read from file and parsed to Int" 
  [filename]
  (->> filename
       (load-lines-to-vec)
       (mapv #(Integer/parseInt %1))))

