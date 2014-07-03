(ns test-project.core-test
  (:require [expectations :refer :all]
            [test-project.core :refer :all]))

(expect 5 (+ 2 3))

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

;#############################################################################################
;; You can use expectations to check that two things are equal by using clojure functions: ###
;; This checks that we have 16 movies in our collection                                    ###
;#############################################################################################

(expect 9 (count movie-collection))

;########################################################################################################################
;; You can also use expectations to check types of things:                                                            ###
;; This checks that the number of movies on our collection is indeed a number (not a string, character, keyword, etc) ###
;########################################################################################################################

(expect number? (:year (first movie-collection)))

;##########################################################################################################################
;; Basically, this means that if we were to call (number? (:year (first movie-collection))), that would result in true: ###
;##########################################################################################################################

(expect false (string? (:year (first movie-collection))))

;#####################
;; This will fail: ###
;#####################

;(expect string? (:year (first movie-collection)))

;##############################################################################################
;; You can also use your own functions that you define instead of using a clojure function: ###
;##############################################################################################

(expect {:title "The Dark Knight" :director "Christopher Nolan" :year 2008 :genre "Action" :length 152 :rating "PG-13"}
        (get-movie-object "The Dark Knight"))

;###################################
;; An example of a failing test: ###
;###################################

;(expect true (movie-object-has-pair? :rating "R" (get-movie-object "Frozen")))

;###############################################################################################################################
;; We can use expectations to see if an element is in a collection. In this case, pairs because we're dealing with hashmaps: ###
;###############################################################################################################################

(expect {:genre "Drama"} (in (get-movie-object "12 Years a Slave")))

;################################################################
;; This will fail because "The Prestige" was not made in 1776 ###
;################################################################

;(expect {:year 1776} (in (get-movie-object "The Prestige")))

;################################################################################
;; More is a macro and can be used to combine multiple checks into one assert ###
;################################################################################

(expect (more vector? not-empty) movie-collection)

(expect (more vector? not-empty #(= 9 (count %))) movie-collection)

;#################################################################################################
;; However, when you want to check things that take more than one argument, you can use more-> ###
;#################################################################################################

(expect (more-> (get-movie-object "Harry Potter and the Sorcerer's Stone") first
                (get-movie-object "The Big Lebowski") last)
        movie-collection)

(expect (more-> "The Hunger Games" :title
                "Gary Ross" :director
                2012 :year
                "Adventure" :genre
                142 :length
                "PG-13" :rating)
        (get-movie-object "The Hunger Games"))

;##################################################################
;; However, you must be careful because order is very specific. ###
;; Here, we've just switched :title and "The Hunger Games"      ###
;##################################################################

;(expect (more-> :title "The Hunger Games"
;                "Gary Ross" :director
;                2012 :year
;                "Adventure" :genre
;                142 :length
;                "PG-13" :rating)
;        (get-movie-object "The Hunger Games"))


;#############################################################################################
;; If you'd like to give a name to the object you're testing, then more-of should be used: ###
;#############################################################################################

(expect (more-of x
                 vector? x
                 not-empty x)
        movie-collection)

(expect (more-of x
                 vector? x
                 not-empty x
                 #(= 9 (count %)) x)
        movie-collection)

;#################################################################
;; More-of is especially nice when your x is more complicated: ###
;#################################################################

(expect (more-of x
                 #(= (:title %) "The Hunger Games") x ;can do predicate of x
                 #(= (:director %) "Gary Ross") x
                 #(= (:year %) 2012) x
                 "Adventure" (:genre x)               ;or can check that two things are equal
                 142 (:length x)
                 "PG-13" (:rating x))
        (get-movie-object "The Hunger Games"))

;#############################################################################################################################
;; Using from-each, we can loop over the elements of some collection and check that all of the elements pass an assertion: ###
;#############################################################################################################################

(expect map?
        (from-each [movie-object movie-collection]
                   movie-object))

;############################################################################################
;; From-each can be combined with :when and :let to narrow down what you want to examine: ###
;############################################################################################

(expect even? (from-each [val (vals (get-movie-object "Argo"))
                          :when (number? val)]
                         val))

(expect odd? (from-each [val (vals (get-movie-object "Argo"))
                         :when (number? val)
                         :let [val-increment (inc val)]]
                         val-increment))

;###########################################################################################################################
;; This below test is the same thing as above, but just with the :when removed. Let's look at what the error message is: ###
;###########################################################################################################################

;(expect odd? (from-each [val (vals (get-movie-object "Argo"))
;                         :let [val-increment (inc val)]]
;                         val-increment))

;################################################################################################
;; Using a variety of more, more->, more-of, and from-each, you can combine together parts to ###
;; create more elaborate and efficient tests:                                                 ###
;################################################################################################

(expect (more map? #(= 6 (count %)))
        (from-each [movie-object movie-collection]
                   movie-object))

;#######################################################################
;; This test uses regular expressions to search for parts of strings ###
;#######################################################################

(expect #"Fellowship" (:title (get-movie-object "The Lord of the Rings: The Fellowship of the Ring")))

(expect #"(.*) Years a Slave" (:title (get-movie-object "12 Years a Slave")))

;#####################################################################################################################################
;; expect-focused is a way to ensure that only those tests (the ones declared with expect-focused) are ran. All others are ignored ###
;#####################################################################################################################################

;(expect-focused 9 (count movie-collection))
