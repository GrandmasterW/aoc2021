(ns aoc2021.util)

;; ----------------------------------------------------------------------
;; transpose
;; ----------------------------------------------------------------------

(defn transpose [m]
  (apply mapv vector m))


;; ----------------------------------------------------------------------
;; misc
;; ----------------------------------------------------------------------

;; a lazy sequence providing values by power of 2: 1 2 4 8...
(def doubler (iterate (partial * 2) 1))

;; ----------------------------------------------------------------------
;; binary and string and int representation conversions
;; ----------------------------------------------------------------------

(defn char-to-int
  "converts a char number like 0, 1 .. 9 to int value 0, 1, .. 9"
  [c]
  (- (int c) (int \0)))

(defn binstr-to-vec
  "converts a vector like '00101' into [0 0 1 0 1]"
  [s]
  (mapv char-to-int (seq s)))

(defn binvec-to-int
  "returns an integer for a vector which contains binary digits, starting with most significant bit: [0 1 1] will yield 3"
  [v]
  (reduce + 0 (mapv * (reverse v) doubler)))


;; ----------------------------------------------------------------------
;; frequency handling
;; ----------------------------------------------------------------------

(defn max-freq
  "returns the element with the highest frequency"
  [f1 f2]
  (cond
    (nil? f1) f2
    (nil? f2) f1
    (> (second f1) (second f2)) f1
    :else f2))

(defn min-freq
  "returns the element with the lowest frequency"
  [f1 f2]
  (cond
    (nil? f1) f2
    (nil? f2) f1
    (< (second f1) (second f2)) f1
    :else f2))
