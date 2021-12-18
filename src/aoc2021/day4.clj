(ns aoc2021.day4
  (:require [clojure.string :as str]
            [aoc2021.load :as ld]
            [aoc2021.util :as ut]))


;; giant squid


(def day4-example-input "src/aoc2021/day4-example-input.txt")
(def day4-full-input "src/aoc2021/day4-full-input.txt")


;; ----------------------------------------------------------------------
;; loading data into structures
;; ----------------------------------------------------------------------

(defn raw-draw-to-vec
  "Converts a string of <int>,<int>,... into a vector of int"
  [line]
  (mapv #(Integer/parseInt %1)
       (str/split line #",")))

(defn make-cell-from-s
  "Create an initial cell for a string representation like '45' -> [45 false]"
  [s]
  [(Integer/parseInt s) false])

(defn board-line-to-row
  "Return a vector of cells for a string containing ints with blanks. Adds a false to each cells number"
  [s]
  (mapv make-cell-from-s
        (remove empty? (str/split s #" "))))
  
(defn raw-to-board
  "convert a sequence of subsequent strings with numbers to a single board. Returns a board as a vector of rows, each row being a vector of number and status."
  [rb]
  (mapv board-line-to-row rb))
  
(defn get-day4-instructions
  "load into structure for bingo.
  Beware: lines with spaces may lead to errors - filter them or remove them in data file!"
  [filename]
  (let [raw (ld/load-lines-to-vec filename)
        drawvec (raw-draw-to-vec (first raw))
        boards (->> (rest raw)
                    (partition-by empty?)
                    (remove (comp empty? first))
                    (mapv raw-to-board))
        ]
    {:draws drawvec
     :boards boards} ))



;; ----------------------------------------------------------------------
;; playing bingo with structures
;; ----------------------------------------------------------------------

(defn apply-number-to-row
  "returns vector of cells, if a number equals n, than with true"
  [n row]
  (mapv #(if (= n (first %)) [n true] %) row))

(defn apply-number-to-board
  "one number, one board, set first occurance (if any) to true"
  [n b]
  ;; mapv assoc
  (mapv (partial apply-number-to-row n) b))

(defn row-all-done?
  "true if all elements of the vector have value true"
  [v]
  (every? (comp true? second) v)) 

;;(def every-vec-done? (partial every? row-all-done?))

(defn board-bingo?
  "true, if one column or row is fully done"
  [b]
  (or (some row-all-done? b)
      (some row-all-done? (ut/transpose b))))

(defn apply-draws
  "subsequently apply bingo numbers to boards until first board is done. Return board and winning number."
  [bs] ; board-structure
  (if (empty? (:draws bs)) bs ; done for bad... 
      (let [draw (first (:draws bs))
            new-boards (mapv (partial apply-number-to-board draw) (:boards bs))
            finished-boards (filterv board-bingo? new-boards)
            ]
        (if  (some seq finished-boards)
          {:draws (:draws bs)
           :last-draw draw
           :bingo (first finished-boards)
           :boards new-boards}
          (recur
           {:draws (rest (:draws bs))
            :boards new-boards})))))


(defn delta-finished-index
  "return i if o is false, n is true"
  [o n i]
  (if (and (not o) n) i))


(defn apply-draws-last
  "subsequently apply bingo numbers to boards until last board is done. Return that board and the last winning number."
  [bs] ; board-structure
  (if (empty? (:draws bs)) bs ; finito
      (let [draw (first (:draws bs))
            new-boards (mapv (partial apply-number-to-board draw) (:boards bs))
            last-finished  (mapv board-bingo? (:boards bs))
            newly-finished (mapv board-bingo? new-boards)
            latest (filterv identity
                            (mapv
                             delta-finished-index
                             last-finished
                             newly-finished
                             (range)))
            ]
        (if (every? board-bingo? new-boards)
          {:draws (:draws bs)
           :last-draw draw
           :boards new-boards
           :last latest
           :last-board (nth new-boards (first latest))
           }
          (recur
           {:draws (rest (:draws bs))
            :boards new-boards})))))

(defn sum-unmarked
  "returns the sum of all unmarked numbers on a board"
  [b]
  (->> b
       (apply concat)
       (filter (comp false? second))
       (map first)
       (reduce +)))
        
;; ----------------------------------------------------------------------
;; result computation
;; ----------------------------------------------------------------------

(defn day4a-result
  "return multiplied results"
  [bs]
  (let [rs (apply-draws bs)
        su (sum-unmarked (:bingo rs))]
    (* su (:last-draw rs))))

(defn day4b-result
  "return multiplied results"
  [bs]
  (let [rs (apply-draws-last bs)
        su (sum-unmarked (:last-board rs))]
    (* su (:last-draw rs))))


