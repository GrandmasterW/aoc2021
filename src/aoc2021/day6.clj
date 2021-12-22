(ns aoc2021.day6
  (:require [clojure.string :as str]
            [aoc2021.load :as ld]
            [aoc2021.util :as ut]))


;; lanternfish
(comment
"
--------------------------------------------------------------------------------
This is a very interesting challenge: the simple rules are easy to compute 
for the first part with 80 steps. A first implementation based on a simple vector 
could use recursion or, even better, a lazy sequence by using iterate. 

However, this will not succeed for 256 steps as the number of Elements grows rapidly. 

A more elaborate approach is to turn the problem around: in fact, we only have to 
keep track of the number of fish / elements for each day in the range 0..8 incl. 
To simulate a day turn we can take the first element, move all other elements one step 
more to the left and add the day 0 elements from the first step. 

And mind the creation rules: 

So, suppose you have a lanternfish with an internal timer value of 3:
After one day, its internal timer would become 2.
After another day, its internal timer would become 1.
After another day, its internal timer would become 0.
After another day, its internal timer would reset to 6, and it would create a new lanternfish with an internal timer of 8.
After another day, the first lanternfish would have an internal timer of 5, and the second lanternfish would have an internal timer of 7.

A lanternfish that creates a new fish resets its timer to 6, not 7 (because 0 is included as a valid timer value). The new lanternfish starts with an internal timer of 8 and does not start counting down until the next day.

--------------------------------------------------------------------------------
")

(def example-input-data [3,4,3,1,2])
                                        ;  "src/aoc2021/day6-example-input.txt"
(def full-input-data
  [1,1,5,2,1,1,5,5,3,1,1,1,1,1,1,3,4,5,2,1,2,1,1,1,1,1,1,1,1,3,1,1,5,4,5,1,5,3,1,3,2,1,1,1,1,2,4,1,5,1,1,1,4,4,1,1,1,1,1,1,3,4,5,1,1,2,1,1,5,1,1,4,1,4,4,2,4,4,2,2,1,2,3,1,1,2,5,3,1,1,1,4,1,2,2,1,4,1,1,2,5,1,3,2,5,2,5,1,1,1,5,3,1,3,1,5,3,3,4,1,1,4,4,1,3,3,2,5,5,1,1,1,1,3,1,5,2,1,3,5,1,4,3,1,3,1,1,3,1,1,1,1,1,1,5,1,1,5,5,2,1,5,1,4,1,1,5,1,1,1,5,5,5,1,4,5,1,3,1,2,5,1,1,1,5,1,1,4,1,1,2,3,1,3,4,1,2,1,4,3,1,2,4,1,5,1,1,1,1,1,3,4,1,1,5,1,1,3,1,1,2,1,3,1,2,1,1,3,3,4,5,3,5,1,1,1,1,1,1,1,1,1,5,4,1,5,1,3,1,1,2,5,1,1,4,1,1,4,4,3,1,2,1,2,4,4,4,1,2,1,3,2,4,4,1,1,1,1,4,1,1,1,1,1,4,1,5,4,1,5,4,1,1,2,5,5,1,1,1,5])
                                        ; "src/aoc2021/day6-full-input.txt"


;; ----------------------------------------------------------------------
;; loading data into structures
;; ----------------------------------------------------------------------

(comment
  (defn str-to-ints
  "converts a string '1,3' into a vector [1 2]"
  [s]
  (mapv ut/parse-int
        (str/split (str/trim s) #",")))

(defn get-input
  "load first row only!"
  [filename]
  (first (mapv str-to-ints (ld/load-lines-to-vec filename))))


) ; comment
 
;; ----------------------------------------------------------------------
;; first implementation for part 1
;; ----------------------------------------------------------------------
(def start-time-old 6)
(def start-time-new 8)

(defn day-turn
  "decrease waiting time, returns an updated vector, new items added at the end"
  [v]
  (let [to-append (count (filterv #(= 0 %) v)) ; number of 0s
        turn (mapv #(if (> % 0) (dec %) start-time-old) v)
        ]
    (into []
          (concat turn (into [] (take to-append (repeat start-time-new)))))))

(defn turns
  "w/o history: simulate n day turns on input, yielding a vector with each turns result"
  ([v n]
   (nth (iterate day-turn v) n)))

;; ----------------------------------------------------------------------
;; Optimized book keeping for part 2
;; For brevity, we call the number tracking structure a book as in accounting 
;; ----------------------------------------------------------------------

(defn make-book
  "Returns a full day 0 to day 8 vector with initial number of elements as provided by vector, example:
   [3 3 2 1 4] -> [0 1 1 2 1 0 0 0 0]"
  [v]
  (let [fs (frequencies v)]
    (mapv #(get fs % 0) (range 0 9))))


(defn book-turn-day
  "turn one day for book b, i.e.
   take number of elements at pos 0,
   construct next book by shifting the rest to the left and
   adding the elements in pos 8"
  [b]
  (assert (= 9 (count b)))
  (let [zeros (first b)
        new-b (into [] (rest b))
        upd-b (update new-b start-time-old (partial + zeros))
        ]
    (conj upd-b zeros)))
  
(defn book-turns
  "returns the book after n days"
  [b n]
  (assert (= 9 (count b)))
  (nth (iterate book-turn-day b) n))

;; ----------------------------------------------------------------------
;; results
;; ----------------------------------------------------------------------

(defn a-result
  "lanternfish, part 1"
  [d n]
  (count (turns d n)))

(defn b-result
  "Lanternfish, part 2, optimized for 256 days"
  [v n]
  (apply + (book-turns (make-book v) n)))
