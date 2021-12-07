(ns aoc2021.load-test
  (:require [clojure.test :as t]
            [aoc2021.load :as ld]
            [aoc2021.day1 :as d1]
            ))


(t/deftest load-lines-to-vec-test
  (let [v-full (ld/load-lines-to-vec d1/day1-input)
        v-int  (ld/load-lines-to-ints d1/day1-input)]
    (t/testing "Both day1 data set contain 2000 elements"
      (t/is (= 2000 (count v-full)))
      (t/is (= 2000 (count v-int))))
    (t/testing "Both day 1 data sets are a vector" 
      (t/is (vector? v-full))
      (t/is (vector? v-int)))
    (t/testing "day 1 vector elements directly after loading: each is a string"
      (t/is (every? string? v-full)))
    (t/testing "day 1 int elements: each is an int"
      (t/is (every? int? v-int)))))

  

