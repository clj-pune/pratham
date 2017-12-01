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
;;              "other-gases"   "argon"  0.33
;;                              "traces" 0.14


;; Looks like JSON? We call it a "hash-map."

{"name" "Earth"
 "mass" 1
 "radius" 1
 "moons" 1
 "atmosphere" {"nitrogen"     78.08
               "oxygen"       20.95
               "CO2"           0.40
               "water-vapour"  0.10
               "other-gases"  {"argon"  0.33
                               "traces" 0.14}}}


;; Let's query the Earth.


;; But first, let's create a global called 'earth', for our convenience.
;; (Warning: global 'def's are an anti-pattern in Clojure.)
(def earth {"name" "Earth"
            "mass" 1
            "radius" 1
            "moons" 1
            "atmosphere" {"nitrogen"     78.08
                          "oxygen"       20.95
                          "CO2"           0.40
                          "water-vapour"  0.10
                          "other-gases"   {"argon"  0.33
                                           "traces" 0.14}}})


;; _Now_ let's query the 'earth' global


;; Top-level access
(get earth "name")
(get earth "mass")
(get earth "moons")

;; Nested access
(get
 (get earth "atmosphere")
 "other-gases")

;; Even more nested access
(get
 (get
  (get earth "atmosphere")
  "other-gases")
 "argon")


;; Let's make our own function to access any "third" level value
(defn get-level-3 [planet level1-key level2-key level3-key]
  (get
   (get
    (get planet level1-key)
    level2-key)
   level3-key))


;; Now we can...
(get-level-3 earth "atmosphere" "other-gases" "argon")
(get-level-3 earth "atmosphere" "other-gases" "traces")



;; Clojure has an interesting thing called "keywords"
;; Think of these as strings on steroids that can do special things
;; We'll see what...
"name" ; a string (evaluates) to itself
:name  ; a keyword evaluates to itself


;; An alternate form of the earth hash-map, with keywords as keys...
;; - that represents the same data,
;; - which is far more convenient to query because of the
;;   special properties of keywords
(def earth-alt {:name "Earth"
                :mass 1
                :radius 1
                :moons 1
                :atmosphere {:nitrogen 78.08
                             :oxygen 20.95
                             :CO2 0.4
                             :water-vapour 0.10
                             :other-gases {:argon 0.33
                                           :traces 0.14}}})

;; Easier top-level access
(:name earth-alt)
(:mass earth-alt)
(:radius earth-alt)

;; Easier nested access
(:argon (:other-gases (:atmosphere earth-alt)))


;; But nested access is so common, we can use Clojure's 'get-in',
;; which is the cousin of 'get' (and the granddaddy of get-level-3 ;-)
(get-in earth-alt [:atmosphere])
(get-in earth-alt [:atmosphere :other-gases])
(get-in earth-alt [:atmosphere :other-gases :argon])

;; Try replacing the keywords with strings
;; like, (get-in earth-alt ["atmosphere"])


;; By the way, our function works with keywords too!
(get-level-3 earth-alt :atmosphere :other-gases :argon)




;; EXTRAS

;; What's special about keywords?
;; they are _also_ callable and runnable and can look
;;   themselves up if used as keys in a hash-map
(class :name)
(supers (class :name))

;; Clojure strings are not special...
;; they're actually just Java strings
(class "name")
(supers (class "name"))


;; MAGIC!
(-> earth-alt :atmosphere :other-gases :traces)
(-> earth-alt :atmosphere :other-gases :argon)
