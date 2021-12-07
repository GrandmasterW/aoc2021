(ns aoc2021.day3-test
  (:require [clojure.test :refer :all]
            [aoc2021.day3 :as d3]))

(deftest gamma-test
  (testing "simple 3 lines gamma value"
    (is (=
         [0 1 0 1 0]
         (d3/gamma [[0 1 0 1 0]
                    [0 1 0 1 0]
                    [1 0 0 1 0]]))))
  (testing "simple 3 lines epsilon value"
    (is (=
         [1 0 0 1 0]
         (d3/epsilon [[0 1 0 1 0]
                      [0 1 0 1 0]
                      [1 0 0 1 0]])))))
