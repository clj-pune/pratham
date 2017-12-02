(ns clj-pune.pratham.p04-map-filter-reduce
  (:require [clj-pune.pratham.common :refer :all]))

;;; map

(def xs [0 1 2 3 4])

(defn increment
  [x]
  (+ x 1))

(map increment xs)

;;; xs                 => [0 1 2 3 4]
;;; (map increment xs) => [1 2 3 4 5]

(map (fn [x]
       (increment x))
     xs)

(map (fn [x y]
       (+ x y))
     xs
     xs)


;; You can generate a new sequence that modifies the sequence
(map inc xs)

;; To generate a sequence of numbers you can use,
(range 5)

;; You can generate a new sequence with different type of contents
(map str
     (range 5))

;; You can generate a new sequence with different shape
(map (fn [x]
       [x x])
     xs)

;; We can generate a sequence of growing sequences
(map (fn [x]
       (range x))
     (range 5))

;; Is there a way to document range input and output ???




;; You can generate a new sequence with different shape
(map (fn [x]
       (if (even? x)
         [x x]
         (str x)))
     xs)

;; Keywords are functions as well so we can pair it with maps
(def planets [{:name "Earth"
               :mass 1.0}
              {:name "Mars"
               :mass 0.107}
              {:name "Venus"
               :mass 0.815}
              {:name "Jupiter"
               :mass 317.83}
              {:name "Saturn"
               :mass 95.162}
              {:name "Mercury"
               :mass 0.0553}])

(map :name planets)

;;; Filter

;;; How to generate a sequence of even numbers

(filter even? (range 5))

(filter odd? (range 5))

;;; Predicates are functions that return true or false values

(def planets [{:name "Earth"
               :mass 1.0}
              {:name "Mars"
               :mass 0.107}
              {:name "Venus"
               :mass 0.815}
              {:name "Jupiter"
               :mass 317.83}
              {:name "Saturn"
               :mass 95.162}
              {:name "Mercury"
               :mass 0.0553}
              {:name "La La Land"
               :mass -0.0553
               :made-up-planet? true
               :creator "Unknown"}])

(defn heavier-than-earth?
  [p]
  (> (:mass p) 1))

(filter heavier-than-earth? planets)


;; Keywords can also be used as predicates if field doesn't exist it
;; returns a nil

(filter :made-up-planet? planets)

;; What if we use :creator

(filter :creator planets)

;; Filter works on truthy and falsey values
;; Truthy is anything but false and nil
;; Falsey valuse are false and nil


;; Sets can be used as predicates

(def habitable-planets #{"Earth" "Mars"})

(filter habitable-planets
        (map :name planets))

;; But we want the original structure we can remove the map
(filter (fn [p]
          (habitable-planets (:name p)))
        planets)

;; Filter does not construct the new sequence from value returned by the
;; function. It uses the value to decide to drop or keep the value

;; This will create a sequence of `true` values
(map (fn [p]
       true)
     planets)

;; This won't create a sequence of `true` values
(filter (fn [p]
          true)
        planets)

;; `identity` is same to function returning the same argument

(map (fn [x]
       x)
     (range 5))

(map identity (range 5))

(filter identity [true false true true false])


;; `comp` is used to compose multiple functions

((comp str inc) 1)

;; Order is important. This won't work ((comp inc str) 1)

;; Find out planets whose mass is positive by using `comp`, keyword and `pos?`
(filter #_(???)
        planets)

;; Find out habitable-planets using `comp`
(filter #_(???)
        planets)


;;; Reduce !!!

;; A simple reduce to sum of sequence (0 1 2 3 4)
(reduce +    [0  1  2  3  4])
       (+(+(+ 0  1) 2  3) 4)

;; | Result | accumulator | head | tail      |
;; |--------+-------------+------+-----------|
;; |      0 |           0 |    0 | [1 2 3 4] |
;; |      1 |           0 |    1 | [2 3 4]   |
;; |      3 |           1 |    2 | [3 4]     |
;; |      6 |           3 |    3 | [4]       |
;; |     10 |           6 |    4 | []        |

;;; Reductions will create sequence of steps of a reduce
(reductions + (range 5))

;;; In reduce function has two arguments unlike map or filter
;;; First one is accumulator and second is one item of the sequence
(reduce (fn [op x]
          (+ op x))
        [0 1 2 3 4])

;;; Reduce has another signature to provide initial value of accumulator

(reduce +
        0
        [0 1 2 3 4])

(reduce +
        50
        [0 1 2 3 4])

;;; What all things can we do with reduce.
;;; A lot of things actually

(reduce *
        [1 2 3 4])

(reduce str
        [0 1 2 3 4])

(reduce str
        [0 1 2 3 4])

;; Find unique elements from a list. don't use function `hash-set` you can use #{}
(reduce (fn [op i]
          )
        #{}
        [1 2 3 3 3 3 3 4 5 6 8])

;; Count number of occurences
(reduce (fn [op i]
          )
        {}
        [1 2 3 3 3 3 3 4 5 6 8])


;; Convert a sequence of tuples into a hash map
(reduce (fn [op [k v]]
          )
        {}
        [[1 2]
         [3 3]
         [3 3]
         [3 4]
         [5 6]])
