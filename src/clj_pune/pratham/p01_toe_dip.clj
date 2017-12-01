(ns clj-pune.pratham.p01-toe-dip
  (:require [clj-pune.pratham.common :refer :all]))

;; Our earth

;; "name" "Earth"
;; "mass" 1  ; if Earth mass is 1, Jupiter is 317.8 Earths
;; "radius" 1 ; if Earth radius is 1, Jupiter radius is 11.21 Earths
;; "moons" 1
;; "atmosphere" "nitrogen"     78.08
;;              "oxygen"       20.95
;;              "CO2"           0.40
;;              "water-vapour"  0.10
;;              "other-gases"   0.47


;; Looks like JSON? We call it a hash-map.

{"name" "Earth"
 "mass" 1
 "radius" 1
 "moons" 1
 "atmosphere" {"nitrogen"     78.08
               "oxygen"       20.95
               "CO2"           0.40
               "water-vapour"  0.10
               "other-gases"   0.47}}


;; Let's query the Earth


;; Note: Inside the 'let' expression below, we have bound 'planet'
;; to our hash-map.
;; For now, just know that a Clojure 'let' creates a local scope
;; within which to name and evaluate things. Ignore the meaning
;; of the square brackets, for now.
(let [planet {"name" "Earth"
              "mass" 1
              "radius" 1
              "moons" 1
              "atmosphere" {"nitrogen"     78.08
                            "oxygen"       20.95
                            "CO2"           0.40
                            "water-vapour"  0.10
                            "other-gases"   0.47}}]
  ;; top-level access
  (get planet "name"))
;; (get planet "mass")
;; (get planet "moons")
;; nested access
;; (get-in planet ["atmosphere" "oxygen"])
;; (get-in planet ["name"]) ; also is valid


;; Note: above, the return value of the let expression is the
;; value of the _last_ thing evaluated.


;; Another form of the hash-map that represents the same data,
;; BUT, with the keys "keyword-ised"
;; AND, which is far more convenient to query... but why?
(let [planet {:name "Earth",
              :mass 1,
              :radius 1,
              :moons 1,
              :atmosphere
              {:nitrogen 78.08,
               :oxygen 20.95,
               :CO2 0.4,
               :water-vapour 0.1,
               :other-gases 0.47}}]

  ;; easier top-level access
  (:name planet)
  (:mass planet)
  (:radius planet)

  ;; nested access
  (:oxygen (:atmosphere planet))
  (get-in planet [:atmosphere :oxygen]))


;; keywords are special in Clojure...
;; they evaluate to themselves, like strings do
;; BUT they are _also_ callable and runnable and can look
;; themselves up if used as keys in a hash-map
(class :name)
(supers (class :name))

;; Clojure strings are not special...
;; they're actually just Java strings
(class "name")
(supers (class "name"))