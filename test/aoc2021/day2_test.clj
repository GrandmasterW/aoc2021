(ns aoc2021.day2-test
  (:require [clojure.test :refer :all]
            [aoc2021.day2 :as d2]))

(deftest convert-instruction-test
  (testing "forward, down, up and value into keyword and int"
      (is (= [:forward 8] (d2/convert-instruction "forward 8")))
      (is (= [:down 1] (d2/convert-instruction "down 1")))
      (is (= [:up 2] (d2/convert-instruction "up 2")))
      )
    (testing "future uses: back, negative values into keyword and int"
      (is (= [:forward -8] (d2/convert-instruction "forward -8")))
      (is (= [:down -1] (d2/convert-instruction "down -1")))
      (is (= [:up -2] (d2/convert-instruction "up -2")))
      (is (= [:back 3] (d2/convert-instruction "back 3")))
      ))

(deftest convert-instruction-str-test
  (testing "simple example string"
    (is (=
         [
          [:forward 5]
          [:down 5]
          [:forward 8]
          [:up 3]
          [:down 8]
          [:forward 2]]
         (d2/convert-instruction-str
"forward 5
down 5
forward 8
up 3
down 8
forward 2")))))

(deftest get-day2-instructions-test
  (testing "loading example file"
    (is (= [
            [:forward 5]
            [:down 5]
            [:forward 8]
            [:up 3]
            [:down 8]
            [:forward 2]]
           (d2/get-day2-instructions d2/day2-example-input)))))

(def initial-position (d2/initial-position))

(deftest update-position-test
  (testing "simple updates"
    (is (= {:horizontal 1 :depth 0 :aim 0}
           (d2/update-position initial-position (d2/convert-instruction "forward 1"))))
    (is (= {:horizontal 0 :depth 1 :aim 0}
           (d2/update-position initial-position (d2/convert-instruction "down 1"))))))

(deftest execute-day2a-test
  (testing "simple commands"
    (is (= {:horizontal 1 :depth 1 :aim 0}
           (d2/execute-day2a
            initial-position
            (d2/convert-instruction-str
"forward 1
down 1"))))
    (is (= {:horizontal 10 :depth 8 :aim 0}
           (d2/execute-day2a
            initial-position
            (d2/convert-instruction-str
"forward 5
down 4
forward 5
down 4")))))
  (testing "example commands"
    (is (= {:horizontal 15 :depth 10 :aim 0}
           (d2/execute-day2a
            initial-position
            (d2/get-day2-instructions d2/day2-example-input))))))

(deftest execute-day2b
  (testing "simple examples"
    (is (= {:horizontal 15 :depth 60 :aim 10}
           (d2/execute-day2b
            initial-position
            (d2/get-day2-instructions d2/day2-example-input)))))
  (testing "simple examples"
    (is (= {:horizontal 2105 :depth 757618 :aim 807}
           (d2/execute-day2b
            initial-position
            (d2/get-day2-instructions d2/day2-full-input))))))

(deftest day2a-result-test
  (testing "example"
    (is (= 150 (d2/day2a-result
               (d2/execute-day2a
                initial-position
                (d2/get-day2-instructions d2/day2-example-input))))))
    (testing "my sample"
    (is (= 1698735 (d2/day2a-result
              (d2/execute-day2a
               initial-position
               (d2/get-day2-instructions d2/day2-full-input)))))))

(deftest day2b-result-test
  (testing "example"
    (is (= 900 (d2/day2a-result
                (d2/execute-day2b
                initial-position
                (d2/get-day2-instructions d2/day2-example-input))))))
  (testing "my sample"
    (is (= 1594785890 (d2/day2a-result
              (d2/execute-day2b
               initial-position
               (d2/get-day2-instructions d2/day2-full-input)))))))
