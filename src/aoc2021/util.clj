(ns aoc2021.util)

;; ----------------------------------------------------------------------
;; transpose
;; ----------------------------------------------------------------------

(defn transpose [m]
  (apply mapv vector m))


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

