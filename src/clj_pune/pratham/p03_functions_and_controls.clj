(ns clj-pune.pratham.p03-functions)

(defn f [x] x)
(def f2 (fn [x] x))

(f 1)
; + is a function, and you can refer to it as a value
(f +)               ; Returns +
;((f2 +) 1 2)
;((f2 f2) 1)
;((f (f2 +)) 2 3 4)
;((f (f *)) 2 3 4)

;; Anonymous functions
(((fn [x] x) +) 1 2 3 4)

;;
((fn [op] (op 1 2 3 4)) +)
((fn [op] (op 1 2 3 4)) *)
