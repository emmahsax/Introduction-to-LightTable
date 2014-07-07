(ns test-project.core)

(def movie-collection
  [{:title "The Ring" :director "Gore Verbinski"
    :year 2002 :genre "Horror" :length 115 :rating "PG-13"}

   {:title "12 Years a Slave" :director "Steve McQueen"
    :year 2013 :genre "Drama" :length 134 :rating "R"}

   {:title "The Hunger Games" :director "Gary Ross"
    :year 2012 :genre "Adventure" :length 142 :rating "PG-13"}

   {:title "Romeo and Juliet" :director "Franco Zeffirelli"
    :year 1968 :genre "Romance" :length 138 :rating "PG-13"}

   {:title "Frozen" :director "Jennifer Lee"
    :year 2001 :genre "Animation" :length 152 :rating "PG"}

   {:title "Argo" :director "Ben Affleck"
    :year 2012 :genre "Drama" :length 120 :rating "R"}

   {:title "The Dark Knight" :director "Christopher Nolan"
    :year 2008 :genre "Action" :length 152 :rating "PG-13"}

   {:title "The Prestige" :director "Christopher Nolan"
    :year 2006 :genre "Drama" :length 130 :rating "R"}

   {:title "The Big Lebowski" :director "Joel Coen"
    :year 1998 :genre "Comedy" :length 117 :rating "R"}])

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

(defn movie-object-has-pair? [k v movie-object]
  (= v (k movie-object)))

(defn get-movie-object [movie-title]
  (first
   (filter #(movie-object-has-pair? :title movie-title %)
           movie-collection)))

(def not-string? (complement string?))

(defn helper-movie-object-has-director? [director movie-object]
  (= director (:director movie-object)))

(defn movie-object-has-director? [director]
  (partial helper-movie-object-has-director? director))
; Partial returns a function that is identical to the first
; argument except with some parameters already given
