(ns clj-pune.pratham.p02-data-structures
  (:require [clj-pune.pratham.common :refer :all]))


;; Let's re-visit Earth, and launch into space...
;; to discover the variety and power of Clojure's Alien data structures.
{:name "Earth"
 :mass 1
 :radius 1
 :moons 1
 :atmosphere {:nitrogen 78.08
              :oxygen 20.95
              :CO2 0.4
              :water-vapour 0.1
              :other-gases {:argon 0.37
                            :traces 0.10}}}

;; Numbers
1
0.1
42e5
22/7

;; Strings
"Earth"
"1"
"42"

;; Keywords
:mass
:mas$
:42


;; Vectors
(vector 1 2 3)

[1 2 3] ; "literal" form of vectors

["a" "b" "c"]

[:a :b :c]

;; Vectors are ORDERED sequences
;; - The following are NOT equal:
(= [1 2 3]
   [1 3 2])


;; Hash-maps
(hash-map :name "Earth" :mass 1 :moons 1)

{:name "Earth"
 :mass 1
 :moons 1} ; "literal" hash-map


;; Hash-maps are very flexible...

;; Hash-maps: all three "literal" variants below are valid, and equal
(= {:name "Earth" :mass 1 :moons 1}

   {:name "Earth", :mass 1, :moons 1}

   {:name "Earth"
    :mass 1
    :moons 1})

;; Hash-maps: are NOT ordered.
;; - We only care about fast key-value look-up.
(= {:name "Earth" :mass 1 :moons 1}
   {:mass 1 :moons 1 :name "Earth"})

;; Hash-maps: can be arbitrarily nested
{:a 1
 :b {:c 2}
 :d {:e {:f 3}
     :g "hello!"}}


;; Hash-maps: can themselves be used as look-up functions of keys
({:a 1 :b 2}  :b)

({"a" 1 :b 2} "a")


;; Sets are available too.
(hash-set 1 2 3 3 3 3)

#{2 1 3}

#{"c" "a" "b"}

#{1 "hello" :b}


;; Sets let us test for membership:
(get #{1 2 3} 1) ; returns 1
(get #{1 2 3} "hello") ; returns nil, when member not found

;; Sets can directly be used as membership functions,
;; because, membership test is such a basic, pervasive use of sets.
(#{1 2 3} 1)

(#{1 2 3} "hello")


;; Sets are un-ordered. We only care about membership.
;; - The following are equal:
(= #{2 1 3}
   #{3 1 2}
   #{1 2 3})



;; BOOM!
;; We can mix-and-match almost anything inside collections:

[1 :foo "bar" {:a 1, :b 2, :c 3} #{"baz" "quxx"}]

#{1 :foo {"bar" "baz"}}

{:a 42
 2 "me-too"
 [:b] "such vector"
 {:c 1} "much map"
 "wow" #{"this" :is 2 :crazy}}


;; BOOOOOOOOOMMMMMMMMM!!!!
;; A Clojure function is just a bunch of nested Clojure Data Structures:
(defn make-set-of-3
  "Given 3 inputs, return a set of them."
  [a b c] ; arg-list vector
  ;; body of the function follows
  (hash-set a b c))

(make-set-of-3 :a "b" 42)


;; Seriously

'a-clojure-symbol ; yet another Clojure primitive

(list "I" :am "a" 'clojure-list)

(eval (list 'defn 'make-set-of-3-more "Make set of 3." ['a 'b 'c] (list 'hash-set 'a 'b 'c)))

(make-set-of-3-more "this" :works 2)
