(ns clj-pune.pratham.p05-higher-order-problem-solving
  (:require [clj-pune.pratham.common :refer :all]))


(defn same
  [x]
  x)

;; 'same' is a function, just as are 'map', 'filter' etc...
(fn? same)
(fn? map)
(fn? filter)

; Same simply returns its input
(= (same 42) 42)

;; A function can accept a function as argument.
(same map)

;; AND, functions are first-class values, that can
;; be accepted, and that can be returned.
(= (same map) map)

(= (same filter) filter)

(= (same same) same)


;; Some more fun with 'same'

;; What's happening here?
(fn? (same same))
(fn? (same (same same)))

;; Why does this equality check evaluate to true?
(= same
   (same same)
   (same (same same))
   (same (same (same same))))


(defn same-same-but-different
  [x]
  (identity x))

;; These three functions are functionally identical to each other,
;; and can be used inter-changeably.
(= (same 42)
   (same-same-but-different 42)
   (identity 42))

;; BUT, of course, they are NOT the same _value_, because they are
;; defined as different functions altogether.
(= same
   same-same-but-different)

(= same
   identity)




;; And now, let's see how to put all our ideas together...


;; We have seen so far:
;; - How to model things with hash-maps and other data structures
;; - How to process data collections with map, filter, some etc.
;; - How to define our own functions (including "predicates")
;; - How to compose functions together into data processing chains
;; - How to pass functions as well as data to other functions



;; LET'S COLONIZE PLANETS!!!!


(def planets
  [{:name "Earth"
    :mass 1 :radius 1 :moons 1
    :atmosphere {:nitrogen 78.08 :oxygen 20.95 :CO2 0.4
                 :water-vapour 0.10 :argon 0.33 :traces 0.14}}

   {:name "Chlorine Planet"
    :mass 2.5 :radius 1.3 :moons 4
    :atmosphere {:chlorine 100.0}}

   {:name "Mercury"
    :mass 0.5 :radius 0.3 :moons 0
    :atmosphere {}}])

(def poison-gases [:chlorine :sulphuric-acid])


(def planet-air-too-poisonous?
  (let [poison-gas-max-pct 0.001]
    (comp (partial some #(> % poison-gas-max-pct))
          (partial remove nil?)
          (apply juxt poison-gases)
          :atmosphere)))

;; Quick-n-dirty test
(filter planet-air-too-poisonous? planets)


(def planet-has-no-atmosphere?
  (comp empty? :atmosphere))

;; Quick-n-dirty test
(filter planet-has-no-atmosphere? planets)


(defn planet-has-all-good-conds?
  [planet good-conditions]
  (every? identity
          ((apply juxt good-conditions)
           planet)))

;; Quick-n-dirty test
(let [earth? (fn [m] (= "Earth" (:name m)))
      good-conds [earth?]]
  (planet-has-all-good-conds? (first planets) good-conds))


(defn planet-has-no-bad-conds?
  [planet bad-conditions]
  (not-any? identity
            ((apply juxt bad-conditions)
             planet)))

;; Quick-n-dirty test
(let [bad-conds [planet-air-too-poisonous?
                 planet-has-no-atmosphere?]]
  (planet-has-no-bad-conds? (first planets) bad-conds))


(defn habitable-planet?
  [good-conditions bad-conditions planet]
  (and (planet-has-all-good-conds? planet good-conditions)
       (planet-has-no-bad-conds? planet bad-conditions)))


;; Quick-n-dirty test
(let [earth? (fn [m] (= "Earth" (:name m)))
      good-conds [earth?]
      bad-conds [planet-air-too-poisonous? planet-has-no-atmosphere?]
      habitable? (partial habitable-planet? good-conds bad-conds)]
  {:habitable (filter habitable? planets)
   :inhospitable (filter (complement habitable?) planets)})


;; Try to...
;; - Add more planets
;; - Add more properties to planets
;; - Define more criteria for habitable planets
;; - Define more criteria for inhospitable planets
;; - Get more creative! The sky ... err, space ... is the limit!
