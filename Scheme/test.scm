(define (sqr x) (* x x))
(define (mult x y) (* x y))
(define x 0) ;; assign x
(define add +) ;; alias + => add
(print x) ;; print x
(set! x (add x 9)) ;; x += 9 using alias
(print x) ;; print x

(define mylist '(1 2 3))

(print mylist)
(print (sqr 9))
(print (mult 15 19))
