(ns aoc2021.day4-test
  (:require [clojure.test :refer :all]
            [aoc2021.day4 :as d4]))


(deftest raw-draw-to-string-test
  (testing ""
    (is (=
        [1 2 3 9 8 7 -1]
        (d4/raw-draw-to-vec "1,2,3,9,8,7,-1")))))

(deftest board-line-to-row-test
  (testing "converting simple line"
    (is (=
         [[1 false][10 false][20 false]]
         (d4/board-line-to-row " 1 10 20")))))

(deftest raw-to-board-test
  (testing "conversion of sequence of strings to board"
    (is (=
         [[[22 false] [13 false] [17 false] [11 false] [0 false]]
          [[8 false] [2 false] [23 false] [4 false] [24 false]]
          [[21 false] [9 false] [14 false] [16 false] [7 false]]
          [[6 false] [10 false] [3 false] [18 false] [5 false]]
          [[1 false] [12 false] [20 false] [15 false] [19 false]]]
         (d4/raw-to-board 
          '("22 13 17 11  0"
           " 8  2 23  4 24"
           "21  9 14 16  7"
           " 6 10  3 18  5"
           " 1 12 20 15 19"))))))


(def ex4res
        {:draws
         [7           4           9           5           11           17           23           2           0           14           21           24           10           16           13           6           15           25           12           22           18           20           8           19          3          26          1],
         :boards
         [[[[22 false] [13 false] [17 false] [11 false] [0 false]]
           [[8 false] [2 false] [23 false] [4 false] [24 false]]
           [[21 false] [9 false] [14 false] [16 false] [7 false]]
           [[6 false] [10 false] [3 false] [18 false] [5 false]]
           [[1 false] [12 false] [20 false] [15 false] [19 false]]]
          [[[3 false] [15 false] [0 false] [2 false] [22 false]]
           [[9 false] [18 false] [13 false] [17 false] [5 false]]
           [[19 false] [8 false] [7 false] [25 false] [23 false]]
           [[20 false] [11 false] [10 false] [24 false] [4 false]]
           [[14 false] [21 false] [16 false] [12 false] [6 false]]]
          [[[14 false] [21 false] [17 false] [24 false] [4 false]]
           [[10 false] [16 false] [15 false] [9 false] [19 false]]
           [[18 false] [8 false] [23 false] [26 false] [20 false]]
           [[22 false] [11 false] [13 false] [6 false] [5 false]]
           [[2 false] [0 false] [12 false] [3 false] [7 false]]]]}
)  

(deftest get-day4-instructions-test
  (testing "loading example"
    (is (= ex4res
           (d4/get-day4-instructions d4/day4-example-input)))))

;; ----------------------------------------------------------------------
(deftest apply-number-to-row-test
  (testing "simple setting a bingo number"
    (is (= [[1 false][99 true][2 false]]
           (d4/apply-number-to-row 99 [[1 false][99 false][2 false]])))
    (is (= [[1 false][99 false][2 false]]
           (d4/apply-number-to-row 100 [[1 false][99 false][2 false]])))
    (is (= [[1 false][99 true][2 false]]
           (d4/apply-number-to-row 99 [[1 false][99 true][2 false]])))
    ))

(deftest apply-number-to-board-test
  (let [orig [ [[1 false][2 false][3 false]]
               [[40 false][50 false][60 false]]
               [[70 false][7 false][79 false]] ]  ]
    (testing "setting one number in a simple board"
      (is (=
           [[[1 false][2 false][3 false]]
            [[40 false][50 true][60 false]]
            [[70 false][7 false][79 false]] ]
           (d4/apply-number-to-board 50 orig))))))
           
(deftest row-all-done?-test
  (testing "simple example of bingo row"
    (is (= true
           (d4/row-all-done?
            [[1 true][2 true][3 true]]))))
  (testing "simple example of bingo row"
    (is (= false
           (d4/row-all-done?
            [[1 false][2 true][3 true]])))))


(deftest board-bingo?-test
  (testing "check if we find bingo rows or columns"
    (is (= true
           (d4/board-bingo? [[[1 false][4 false][7 false]]
                             [[2 false][5 false][8 false]]
                             [[3 true][6 true][9 true]]
                             ])))
        (is (= true
           (d4/board-bingo? [[[1 true][4 false][7 false]]
                             [[2 true][5 false][8 false]]
                             [[3 true][6 false][9 false]]
                             ]))))
    (testing "check if we fail to find bingo rows or columns"
      (is (not
             (d4/board-bingo? [[[1 false][4 false][7 false]]
                               [[2 false][5 false][8 false]]
                               [[3 true][6 false][9 true]]
                               ])))
      (is (not
             (d4/board-bingo? [[[1 false][4 false][7 false]]
                               [[2 false][5 false][8 false]]
                               [[3 false][6 false][9 false]]
                               ])))))

(deftest apply-draws-test
  (testing "simple 2x2 example with two boards, find first one with first row"
    (let [bs {:draws [1 2 5 3]
              :boards
              [
               [[[1 false][2 false]]
                [[3 false][4 false]]]
               [[[3 false][4 false]]
                [[5 false][6 false]]]
               ]}
          rs (d4/apply-draws bs) ]
      (is (= 2 (:last-draw rs)))
      (is (=   [[[1 true] [2 true]]
                [[3 false][4 false]]]
               (:bingo rs)))))
  (testing "simple 2x2 example with two boards, find 2nd with first column"
    (let [bs {:draws [3 5 6 1]
              :boards
              [
               [[[1 false][2 false]]
                [[3 false][4 false]]]
               [[[3 false][4 false]]
                [[5 false][6 false]]]
               ]}
          rs (d4/apply-draws bs) ]
      (is (= 5 (:last-draw rs)))
      (is (=   [[[3 true][4 false]]
                [[5 true][6 false]]]
               (:bingo rs))))))

(deftest apply-draws-last-test
  (testing "simple 2x3 example with 3 boards, find middle board"
    (let [bs {:draws [4 3 10 9 6 18],
              :boards [[[[1 false] [2 false]] [[3 false] [4 false]]]
                       [[[4 false] [5 false]] [[6 false] [7 false]]]
                       [[[7 false] [8 false]] [[9 false] [10 false]]]]}
          rs (d4/apply-draws-last bs)
          ]
    (is (= 6 (:last-draw rs)))
    (is (= [[[4 true] [5 false]] [[6 true] [7 false]]]
           (:last-board rs))))))

(deftest sum-unmarked-test
  (testing "simple 2x2 example"
    (is (= 3
           (d4/sum-unmarked [[[1 false] [2 false]] [[3 true] [4 true]]])))))

(deftest day4a-result-test
  (testing "example-result"
    (is (= 4512
           (d4/day4a-result (d4/get-day4-instructions d4/day4-example-input)))))
  (testing "puzzle input"
    (is (= 51034
           (d4/day4a-result (d4/get-day4-instructions d4/day4-full-input)))))
  )

(deftest day4b-result-test
  (testing "example-result"
    (is (= 1924
           (d4/day4b-result (d4/get-day4-instructions d4/day4-example-input)))))
  (testing "puzzle input"
    (is (= 5434
           (d4/day4b-result (d4/get-day4-instructions d4/day4-full-input)))))
  )
