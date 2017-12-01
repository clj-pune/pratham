(ns clj-pune.pratham.p03-functions-and-controls
  (:require [clojure.repl :refer [doc source]]))

;; These two are equivalent
;; Both return their arguments
(defn f [x] x)
(def f' (fn [x] x))

;; '+' is a function. But you can pass it around as a value
(f 1)
(f +)
;; So, whether you pass '1' or '+' to f, it simply returns that back.


;; (f' +) returns '+'. So, same as (+ 1 2)
((f' +) 1 2)

;; f' is 'identity'. Passing it to itself, returns itself.
;; So, this is just 1
((f' f') 1)

;; More more level of invocation. But it's still the identity operation
;; Equivalent to (+ 2 3 4)
((f (f' +)) 2 3 4)

;; Equivalent to (* 2 3 4)
((f (f *)) 2 3 4)

;; Since functions are just values, they can be invoked with an 'alias'
;; Here, op is set to the addition function '+'
(let [op +]
  (op 1 2 3 4))

;; op is multiplication - '*'
(let [op *]
  (op 1 2 3 4))

;; Anonymous functions
;; You need not give them names. Just use them in place.
(((fn [x] x) +) 1 2)

;; The '+' and the '*' at the end are the arguments to the functions
;; The arguments are used at the function invocation position '(op ...'
((fn [op] (op 1 2)) +)
((fn [op] (op 1 2)) *)

;; More of the same
;; When you don't want to name your function or the arguments
;; % is the first argument. %2 second. %3 third. And so on.

;; + is the first argument to #(% 1 2). So, equivalent to (+ 1 2)
(#(% 1 2) +)

;; Same with *
(#(% 1 2) *)

;; + and * also have 0-arity. They return the 'unit' values for
;; those operations. Confirm by uncommenting and executing?
;(+)
;(*)

;; A useless addition function.
(defn plus [a b] (+ a b))
(plus 5 10)

;; A more pointless plus' function. Supports more arities.
(defn plus'
  ([a b] (+ a b))
  ([a b c] (+ a b c))
  ([a b c d] (+ a b c d))
  ([a b c d e] (+ a b c d e)))

; Does the following work?
;(plus' 1 2 3 4 5 6)

; Even better plus? Notice how long the argument list is?
(+ 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15)

;; Scene 1 - over

;; Flow-control

;; if is an expression. And if expression evaluates to a value
(defn silent-max [a b]
  (if (> a b)
    a
    b))
;; Which is the return value?
(silent-max 10 20)

;; Better max?
(max 1 2 3 4 5 6 7 8 9 10 9 8 7 6 5 4 3 2 1)

;; Multi-steps? do block...
(defn chatty-max [a b]
  (if (> a b)
    ;; True block
    (do (println "The first argument.")
        ;; More steps can go here.
        ;; But only the last one is 'valuable'
        a)

    ;; The else part.
    (do (println "The second argument.")
        b)))

;; 'when' we are only interested in the positive case...
(defn pamper-high-value-customer [money]
  (when (> money 100)
    (println "Hello, sir!")
    (println "What would you like, sir?")
    (println "A creamy mocha, and dark chocolate cookies await you...")
    [:mocha :cookie]))

(pamper-high-value-customer 10)
(pamper-high-value-customer 110)

;; Notice how easy it is to return multiple values,
;; using the vector literal

;; A do block (or a when) isn't very useful when the multiple expressions
;; within need to feed each other. A 'let' block works better
(defn reverse-and-capitalize [s]
  (let [s     (seq s)
        rev-s (reverse s)
        fst   (first rev-s)
        rst   (rest rev-s)]
    ;; All the above variables are accessible underneath
    (apply str (Character/toTitleCase fst) rst)))

;(reverse-and-capitalize "hello world")

;; 'cond'itional execution paths
(defn grade [marks]
  (cond
    (< marks 36) :poor
    (< marks 76) :average
    (< marks 91) :exceptional
    :else :genius!))

(grade 10)
(grade 50)
(grade 90)

; Make the 'grade' function return :stupid when marks > 100
; (grade 150)

(defn cgpa [grade]
  (case grade
    :A 4.0
    :B 3.0
    :C 2.0
    0.0))

;(cgpa :A)
;(cgpa :zzzzz)

;; A Clojure function is just a bunch of nested Clojure Data Structures:
(defn make-set-of-3
  "Given 3 inputs, return a set of them."
  [a b c]           ; arg-list vector
  ;; body of the function follows
  (hash-set a b c))

(make-set-of-3 :a "b" 42)
(eval (list 'defn 'make-set-of-3-more
            "Make set of 3."
            ['a 'b 'c]
            (list 'hash-set 'a 'b 'c)))
(make-set-of-3-more "this" :works 2)

(defn a-well-decorated-function
  "Well documented function"
  {:private true
   :author "Donald Duck"}
  [i c u]
  {:pre (true)}
  "I am a happy function because I ignore all arguments!")

; (a-well-decorated-function 1 2 3)
; (meta #'a-well-decorated-function)

;; What is #'a-well-decorated-function ?
; (class #'a-well-decorated-function)
; (class a-well-decorated-function)

;(class #'+)
;(class +)
;(source +)
;(doc +)
