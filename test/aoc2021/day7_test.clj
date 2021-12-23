(ns aoc2021.day7-test
  (:require [clojure.test :refer :all]
            [aoc2021.day7 :as d]))


;; ----------------------------------------------------------------------
;; 
;; ----------------------------------------------------------------------
(deftest pos-cost-test
  (testing "example data variations"
    (is (= 37 (d/pos-cost 2 d/example-data)))
    (is (= 41 (d/pos-cost 1 d/example-data)))
    (is (= 39 (d/pos-cost 3 d/example-data)))
    (is (= 71 (d/pos-cost 10 d/example-data)))
    ))


(deftest min-cost-test
  (testing "example data"
    (is (= 37 (d/min-cost d/example-data)))))

;; ----------------------------------------------------------------------
;; results
;; ----------------------------------------------------------------------

(deftest a-result-test
  (testing "example data"
    (is (= 37 (d/a-result d/example-data))))
  (testing "input data"
    (is (= 337833 (d/a-result d/full-data)))))

    
  
