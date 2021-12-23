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

(deftest b-cost-test
  (testing "example rows"
    (is (= 66 (d/b-cost 16 5)))
    (is (= 10 (d/b-cost 1 5)))
    (is (= 6 (d/b-cost 2 5)))
    (is (= 15 (d/b-cost 0 5)))
    (is (= 1 (d/b-cost 4 5)))))


(deftest b-pos-cost-test
  (testing "example rows"
    (is (= 97 (d/b-pos-cost 5 [16 1 2 0])))))

(deftest min-cost-test
  (testing "example data"
    (is (= 37 (d/min-cost d/example-data d/pos-cost)))))

;; ----------------------------------------------------------------------
;; results
;; ----------------------------------------------------------------------

(deftest a-result-test
  (testing "example data"
    (is (= 37 (d/a-result d/example-data))))
  (testing "input data"
    (is (= 337833 (d/a-result d/full-data)))))

(deftest b-result-test
  (testing "example data"
    (is (= 168 (d/b-result d/example-data))))
  (testing "input data"
    (is (= 96678050 (d/b-result d/full-data)))))
    
  
