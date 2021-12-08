(ns aoc2021.util-test
  (:require [clojure.test :refer :all]
            [aoc2021.util :as ut]))

;; not testing transpose...

;; ----------------------------------------------------------------------
;; conversions
;; ----------------------------------------------------------------------

(deftest char-to-int-test
  (testing " to 9"
    (is (= 9 (ut/char-to-int \9))))
  (testing " to 0"
    (is (= 0 (ut/char-to-int \0)))))
  
(deftest binstr-to-vec-test
  (testing "'101001' to [1 0 1 0 0 1]"
    (is (= [1 0 1 0 0 1]
           (ut/binstr-to-vec "101001")))))

(deftest binvec-to-int
  (testing "[1 0 1 0] to 10"
    (is (= 10
           (ut/binvec-to-int [1 0 1 0])))))



;; ----------------------------------------------------------------------
;; max-freq and min-freq
;; ----------------------------------------------------------------------
(deftest max-freq-test
  (testing "simple set of 2"
    (is (= [0 2] (ut/max-freq [0 2][1 1]))))
  (testing "simple set of with last one to be chosen"
    (is (= [2 3] (ut/max-freq [0 2] [2 3])))))
  
(deftest min-freq-test
  (testing "simple set of 2"
    (is (= [1 1] (ut/min-freq [0 2][1 1])))))

