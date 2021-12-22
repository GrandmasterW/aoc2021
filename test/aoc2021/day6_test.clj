(ns aoc2021.day6-test
  (:require [clojure.test :refer :all]
            [aoc2021.day6 :as d]))


;; ----------------------------------------------------------------------
;; test mutate data for part 1
;; ----------------------------------------------------------------------
(def part1-example
  [
   [3,4,3,1,2] ;; initial
   [2,3,2,0,1] ;; After  1 day: 
[1,2,1,6,0,8] ;; After  2 days: 
[0,1,0,5,6,7,8] ;; After  3 days: 
[6,0,6,4,5,6,7,8,8] ;; After  4 days: 
[5,6,5,3,4,5,6,7,7,8] ;; After  5 days: 
[4,5,4,2,3,4,5,6,6,7] ;; After  6 days: 
[3,4,3,1,2,3,4,5,5,6] ;; After  7 days: 
[2,3,2,0,1,2,3,4,4,5] ;; After  8 days: 
[1,2,1,6,0,1,2,3,3,4,8] ;; After  9 days: 
[0,1,0,5,6,0,1,2,2,3,7,8] ;; After 10 days: 
[6,0,6,4,5,6,0,1,1,2,6,7,8,8,8] ;; After 11 days: 
[5,6,5,3,4,5,6,0,0,1,5,6,7,7,7,8,8] ;; After 12 days: 
[4,5,4,2,3,4,5,6,6,0,4,5,6,6,6,7,7,8,8] ;; After 13 days: 
[3,4,3,1,2,3,4,5,5,6,3,4,5,5,5,6,6,7,7,8] ;; After 14 days: 
[2,3,2,0,1,2,3,4,4,5,2,3,4,4,4,5,5,6,6,7] ;; After 15 days: 
[1,2,1,6,0,1,2,3,3,4,1,2,3,3,3,4,4,5,5,6,8] ;; After 16 days: 
[0,1,0,5,6,0,1,2,2,3,0,1,2,2,2,3,3,4,4,5,7,8] ;; After 17 days: 
[6,0,6,4,5,6,0,1,1,2,6,0,1,1,1,2,2,3,3,4,6,7,8,8,8,8] ;; After 18 days: 
])

(def part1-books
  (mapv d/make-book part1-example))

(comment
(deftest str-to-ints
  (testing "converting a string '1,2,3' into vector [1 2 3]"
    (is (= [0 1 -2 3 4 5] (d/str-to-ints "0,1,-2,3,4,5")))
    ))

(deftest get-input-test
  (testing "example-input"
    (is (= [3 4 3 1 2]
         (d/get-input d/example-input)))))
)
;; ----------------------------------------------------------------------


(deftest day-turn-test
  (testing "example flow"
    (is (= [2 3 2 0 1] (d/day-turn [3 4 3 1 2])))))

(deftest turns-test
  (testing "example input for 18 days" 
    (is (= (last part1-example)
           (d/turns d/example-input-data 18)))))

;; ----------------------------------------------------------------------
;; Tests for part 2 optimizations
;; ----------------------------------------------------------------------

(deftest make-book-test
  (testing "simple examples"
    (is (= [0 1 1 2 1 0 0 0 0]
           (d/make-book   [3 3 2 1 4])))
    (is (= [1 1 1 1 1 1 1 1 1]
           (d/make-book   [0 1 2 3 4 5 6 7 8]))))
  (testing "input data"
    (is (= [0 1 1 2 1 0 0 0 0]
           (d/make-book d/example-input-data)))
    (is (= [0 155 31 33 38 43 0 0 0]
           (d/make-book d/full-input-data)))))

(deftest book-turn-day-test
  (testing "simple examples"
    (is (= [1 2 3 4 0 0 0 0 0]
           (d/book-turn-day [0 1 2 3 4 0 0 0 0])))
    (is (= [2 3 4 0 0 0 1 0 1]
           (d/book-turn-day [1 2 3 4 0 0 0 0 0]))))
  (testing "input data examples"
    (is (= (drop 1 part1-books) ; first is initial, hence not contained in output
           (butlast ;; result is one day more, ignore that one
            (mapv (comp d/book-turn-day d/make-book) part1-example))))))
  

;; ----------------------------------------------------------------------

(deftest a-result-test
  (testing "example result"
    (is (= 5934 (d/a-result d/example-input-data 80))))
  (testing "puzzle result"
    (is (= 372300 (d/a-result d/full-input-data 80)))))

(deftest b-result-test
  (testing "example result"
    (is (= 5934 (d/b-result d/example-input-data 80))))
  (testing "puzzle result"
    (is (= 372300 (d/b-result d/full-input-data 80))))
  (testing "example data result with 256 days"
    (is (= 26984457539 (d/b-result d/example-input-data 256))))
  (testing "puzzle result"
    (is (= 1675781200288 (d/b-result d/full-input-data 256))))
  )
  
