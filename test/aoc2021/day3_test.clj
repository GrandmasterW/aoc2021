(ns aoc2021.day3-test
  (:require [clojure.test :refer :all]
            [aoc2021.day3 :as d3]))

(deftest gamma-test
  (testing "simple 3 lines gamma value"
    (is (=
         [0 1 0 1 0]
         (d3/gamma [[0 1 0 1 0]
                    [0 1 0 1 0]
                    [1 0 0 1 0]])))))

(deftest epsilon-test 
  (testing "simple 3 lines epsilon value"
    (is (=
         [1 0 0 1 0]
         (d3/epsilon [[0 1 0 1 0]
                      [0 1 0 1 0]
                      [1 0 0 1 0]])))))

(deftest oxygen-test
  (testing "example should return 10111"
    (is (= [1 0 1 1 1]
           (d3/oxygen (d3/get-day3-instructions d3/day3-example-input))))))

(deftest co2-test
  (testing "example should return 01010"
    (is (= [0 1 0 1 0]
           (d3/co2 (d3/get-day3-instructions d3/day3-example-input))))))

(deftest day3a-result-test
  (testing "example value"
    (is (= 198
           (d3/day3a-result (d3/get-day3-instructions d3/day3-example-input)))))
  (testing "input value"
    (is (= 693486
           (d3/day3a-result (d3/get-day3-instructions d3/day3-full-input))))))

(deftest day3b-result-test
  (testing "example value"
    (is (= 230
           (d3/day3b-result (d3/get-day3-instructions d3/day3-example-input)))))
    (testing "puzzle input value"
    (is (= 3379326
           (d3/day3b-result (d3/get-day3-instructions d3/day3-full-input))))))
