(ns aoc2021.day5-test
  (:require [clojure.test :refer :all]
            [aoc2021.day5 :as d5]))


(deftest str-to-point-test
  (testing "converting a string into a point, variations"
    (is (= [1 2] (d5/str-to-point "1,2")))
    (is (= [3 4] (d5/str-to-point "3,4")))
    (is (= [3 4] (d5/str-to-point " 3,4")))
    (is (= [3 4] (d5/str-to-point "3,4 ")))
    ))


(deftest line-coords-v-test
  (testing "simple line coordinates with spaces"
    (is (= [[45 2][45 10]] (d5/line-coords-v "45,2 -> 45,10")))
    (is (= [[6 4][2 0]] (d5/line-coords-v "6,4 -> 2,0")))
    (is (= [[6 4][2 0]] (d5/line-coords-v "6,4->2,0")))
    ))

;; ----------------------------------------------------------------------

(deftest get-input-test
  (testing "example-input"
    (is (= [[[0 9] [5 9]]
            [[8 0] [0 8]]
            [[9 4] [3 4]]
            [[2 2] [2 1]]
            [[7 0] [7 4]]
            [[6 4] [2 0]]
            [[0 9] [2 9]]
            [[3 4] [1 4]]
            [[0 0] [8 8]]
            [[5 5] [8 2]]]
           (d5/get-input d5/example-input)))))


(deftest is-ortho?-test
  (testing "simple examples"
    (is (d5/is-ortho? [[0 0][0 10]]))
    (is (d5/is-ortho? [[-1 -2][-20 -2]]))
    (is (not (d5/is-ortho? [[0 0][10 10]])))
    (is (not (d5/is-ortho? [[-1 -1][10 10]])))))
    
(deftest xyrange-test
  (testing "ortho and diagonal"
    (is (= '(1 2 3) (d5/xyrange 1 3)))
    (is (= '(5 4 3 2) (d5/xyrange 5 2)))))
    


(deftest line-points-test
  (testing "creating points from line coords, simple version"
    (is (= [[1 10] [2 10] [3 10] [4 10] [5 10] [6 10] [7 10] [8 10] [9 10] [10 10]]
           (d5/line-points [[1 10][10 10]])))
    (is (= [[1 0] [1 1] [1 2] [1 3] [1 4] [1 5] [1 6] [1 7] [1 8] [1 9] [1 10]]
           (d5/line-points [[1 0][1 10]])))))

(deftest overlaps-test
  (testing "example input overlaps: 5"
    (is (= [[7 4] [3 4] [1 9] [2 9] [0 9]]
           (->> d5/example-input
                (d5/get-input)
                (filterv d5/is-ortho?)
                (mapv d5/line-points)
                (mapcat identity)
                (d5/overlaps))))))
                
;; ----------------------------------------------------------------------

(deftest day5a-result-test
  (testing "example result"
    (is (= 5 (d5/day5a-result d5/example-input))))
  (testing "puzzle result"
    (is (= 6461 (d5/day5a-result d5/full-input))))
  )

(deftest day5b-result-test
  (testing "example result"
    (is (= 12 (d5/day5b-result d5/example-input))))
  (testing "puzzle result"
    (is (= 18065 (d5/day5b-result d5/full-input))))
  )
