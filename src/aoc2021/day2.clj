(ns aoc2021.day2
  (:require [clojure.string :as s]))

(def day2-example-input  "src/aoc2021/day2-example-input.txt")
(def day2-full-input  "src/aoc2021/day2-input.txt")

;; ----------------------------------------------------------------------
;; instructions
;; ----------------------------------------------------------------------

(defn convert-instruction
  "converts a string like 'forward 8' into [:forward 8]"
  [s]
  (let [vs (s/split s #" ")]
    (vector (keyword (first vs))
            (Integer/parseInt (second vs)))))


(defn convert-instruction-str
  "For a string containing 'direction value' lines, it returns a vector of vectors, each vector consisting of an instruction and a value. The instruction is a keyword, while the value is an integer"
  [v]
  (->> v
       (s/split-lines)
       (mapv convert-instruction)))

(defn get-day2-instructions
  "returns a vector of direction vectors" 
  [filename]
  (->> filename
       (slurp)
       (convert-instruction-str)))

;; ----------------------------------------------------------------------
;; positioning
;; ----------------------------------------------------------------------

(defn initial-position
  "create the initial position as {:horizontal 0, :depth 0}"
  []
  {:horizontal 0
   :depth 0
   :aim 0})

(defn upd-plus
  [m k v]
  (update m k (partial + v)))

(defn update-position
  "returns the new position :horizontal and :depth by updating the given position with one command"
  [position [direction value]]
  (case direction
    :forward (upd-plus position :horizontal value)
    :down (upd-plus position :depth value)
    :up (upd-plus position :depth (- value))))

(defn update-aim
  "returns the new position :horizontal, :aim and :depth by updating the given position with one command"
  [position [direction value]]
  (case direction
    :down (upd-plus position :aim value)
    :up (upd-plus position :aim (- value))
    :forward (-> position
                 (upd-plus :horizontal value)
                 (upd-plus :depth (* (:aim position) value)))))

(defn execute-commands
  "takes a vector with commands and executes it to given position, returns resulting position"
  [upd-fn position v]
  (if (not (seq v)) position
      (execute-commands
       upd-fn
       (upd-fn position (first v))
       (rest v))))

(defn execute-day2a
  "calls execute-commands with update-position" 
  [position v]
  (execute-commands update-position position v))

(defn execute-day2b
  "calls execute-commands with update-aim" 
  [position v]
  (execute-commands update-aim position v))

;; ------------------------------------------------------------
;; result computation
;; ----------------------------------------------------------------------

(defn day2a-result
  "hor * dep"
  [position]
  (* (:horizontal position)(:depth position)))

