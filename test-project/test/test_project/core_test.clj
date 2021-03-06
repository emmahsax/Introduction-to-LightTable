(ns test-project.core-test
  (:require [expectations :refer :all]
            [test-project.core :refer :all]))

;##################################################################################
;; You can use Expectations to check that two things are equal using this form: ###
;; (expect expected actual).                                                    ###
;##################################################################################

;(expect 5 (+ 2 3))

;(expect 9 (count movie-collection))

;(expect "Ben Affleck" (:director (get-movie-object "Argo")))

;(expect "R" (:rating (get-movie-object "The Prestige")))

;; This will fail
;(expect 1752 (:year (get-movie-object "The Ring")))

;#########################################################################
;; These tests use regular expressions to search for parts of strings. ###
;#########################################################################

;(expect #"van" "nirvana")

;(expect #"Juliet" (:title (get-movie-object "Romeo and Juliet")))

;(expect #"(.*) Years a Slave" (:title (get-movie-object "12 Years a Slave")))

;; This will fail
;(expect #"(.+) Obama" (:director (get-movie-object "Frozen")))

;######################################################################
;; We can use Expectations to see if an element is in a collection. ###
;######################################################################

;(expect 5 (in #{1 3 5 7}))

;(expect {:genre "Drama"} (in (get-movie-object "12 Years a Slave")))

;(expect {:year 2012} (in (get-movie-object "The Hunger Games")))

;; This will fail
;(expect {:year 1776} (in (get-movie-object "The Prestige")))

;###########################################################################
;; You can also use Expectations using a predicate as the first argument ###
;; and something else as the second argument.                            ###
;; A predicate is a function that returns true or false.                 ###
;###########################################################################

;(expect number? 2093)

;(expect true? (number? 4))

;(expect zero? 0)

;(expect char? \h)

;(expect list? '(:this :is :an :example))

;(expect number? (:year (first movie-collection)))

;(expect not-string? (:year (get-movie-object "Frozen")))

;; This will fail
;(expect number? (:genre (get-movie-object "The Big Lebowski")))

;###########################################################################
;; Above, a predicate is used as the first argument, but we could write  ###
;; the same test by clarifying whether we expect true or false, and then ###
;; calling the predicate explicitly in the second argument.              ###
;###########################################################################

;(expect true (number? (:year (first movie-collection))))

;(expect true (not-string? (:year (get-movie-object "Frozen"))))

;(expect false (number? (:director (get-movie-object "Frozen"))))

;###########################################
;; Example of how partial would be used: ###
;###########################################

;(expect (helper-movie-object-has-director? "Steve McQueen" (get-movie-object
;                                                            "12 Years a Slave")))

;; This will throw an error
;; The error is caused by a problem in the test syntax, not the code.
;(expect (helper-movie-object-has-director? "Steve McQueen") (get-movie-object
;                                                             "12 Years a Slave"))

;(expect (movie-object-has-director? "Steve McQueen") (get-movie-object
;                                                      "12 Years a Slave"))

;#########################################################################
;; If you are expecting a piece of code to throw an exception, you can ###
;; test for it like this:                                              ###
;#########################################################################
;(expect ClassCastException (+ :one :two))

;(expect Exception ((helper-movie-object-has-director? "Steve McQueen")
;                   (get-movie-object "12 Years a Slave")))

;#################################################################################
;; More is a macro and can be used to combine multiple checks into one assert. ###
;#################################################################################

;(expect (more vector? not-empty) movie-collection)

;(expect (more vector? not-empty #(= 9 (count %))) movie-collection)

;; This will fail
;(expect (more set? not-empty #(= 9 (count %))) movie-collection)

;##############################################################################
;; However, when you want to check things that take more than one argument, ###
;; you can use more->.                                                      ###
;##############################################################################

;(expect (more-> (get-movie-object "The Ring") first
;                (get-movie-object "The Big Lebowski") last)
;        movie-collection)

;(expect (more-> "The Hunger Games" :title
;                "Gary Ross" :director
;                2012 :year
;                "Adventure" :genre
;                142 :length
;                "PG-13" :rating)
;        (get-movie-object "The Hunger Games"))

;##################################################################
;; However, you must be careful because order is very specific. ###
;; Here, we've just switched :title and "The Hunger Games".     ###
;##################################################################

;; This will throw an error
;(expect (more-> :title "The Hunger Games"
;                "Gary Ross" :director
;                2012 :year
;                "Adventure" :genre
;                142 :length
;                "PG-13" :rating)
;        (get-movie-object "The Hunger Games"))

;#############################################################################
;; If you'd like to give a name to the object you're testing, then more-of ###
;; should be used:                                                         ###
;#############################################################################

;(expect (more-of x
;                 vector? x
;                 not-empty x)
;        movie-collection)

;(expect (more-of x
;                 vector? x
;                 not-empty x
;                 #(= 9 (count %)) x)
;        movie-collection)

;#################################################################
;; More-of is especially nice when your x is more complicated: ###
;#################################################################

;(expect (more-of x
;                 #(= (:title %) "The Hunger Games") x ;can do predicate of x
;                 #(= (:director %) "Gary Ross") x
;                 #(= (:year %) 2012) x
;                 "Adventure" (:genre x)   ;or can check that two things are equal
;                 142 (:length x)
;                 "PG-13" (:rating x))
;        (get-movie-object "The Hunger Games"))

;###########################################################################
;; Using from-each, we can loop over the elements of some collection and ###
;; check that all of the elements pass an assertion:                     ###
;###########################################################################

;(expect map?
;        (from-each [movie-object movie-collection]
;                   movie-object))

;###########################################################################
;; From-each can be combined with :when and :let to narrow down what you ###
;; want to examine:                                                      ###
;###########################################################################

;(expect even? (from-each [val (vals (get-movie-object "Argo"))
;                          :when (number? val)]
;                         val))

;(expect odd? (from-each [val (vals (get-movie-object "Argo"))
;                         :when (number? val)
;                         :let [val-increment (inc val)]]
;                         val-increment))

;##################################################################################
;; This below test is the same thing as above, but just with the :when removed. ###
;##################################################################################

;; This will throw an error
;(expect odd? (from-each [val (vals (get-movie-object "Argo"))
;                         :let [val-increment (inc val)]]
;                         val-increment))

;##############################################################################
;; Using a variety of more, more->, more-of, and from-each, you can combine ###
;; parts together to create more elaborate tests:                           ###
;##############################################################################

;(expect (more map? #(= 6 (count %)))
;        (from-each [movie-object movie-collection]
;                   movie-object))

;################################################################################
;; Expect-focused is a way to ensure that only those tests (the ones declared ###
;; with expect-focused) are ran. All others are ignored.                      ###
;################################################################################

;(expect-focused 9 (count movie-collection))
