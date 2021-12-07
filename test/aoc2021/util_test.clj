(ns aoc2021.util-test
  (:require [clojure.test :refer :all]
            [aoc2021.util :as ut]))

;; not testing transpose...


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

