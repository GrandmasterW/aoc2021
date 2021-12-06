(ns aoc2021.day1-test
  (:require [clojure.test :refer :all]
            [aoc2021.day1 :as d1]))


(deftest sum3-test
  (let [v d1/example-report
        sv [607 618 618 617 647 716 769 792]]
    (testing "sum3 with example"
      (is (= sv (d1/sum3 v))))))


(deftest count-inc-test
  (let [v-ex d1/example-report
        v-full (d1/get-day1-numbers d1/day1-input)]
    (testing "Day1 a) example from explanation"
      (is (= 7 (d1/count-inc v-ex))))
    (testing "Day1 a) my input set"
      (is (= 2000 (count v-full))))
    (testing "Day1 a) my result"
      (is (= 1754 (d1/count-inc v-full))))
    (testing "Day1 b) 3-values-sum example"
      (is (= 5 (d1/count-win3 v-ex))))
    (testing "Day1 b) my input set result"
      (is (= 1789 (d1/count-win3 v-full))))
    ))

  

