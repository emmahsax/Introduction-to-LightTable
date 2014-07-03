(ns test-project.core)

(def movie-collection
  [{:title "Harry Potter and the Sorcerer's Stone" :director "Chris Columbus" :year 2001 :genre "Fantasy" :length 152 :rating "PG"}

   {:title "12 Years a Slave" :director "Steve McQueen" :year 2013 :genre "Drama" :length 134 :rating "R"}

   {:title "The Hunger Games" :director "Gary Ross" :year 2012 :genre "Adventure" :length 142 :rating "PG-13"}

   {:title "The Lord of the Rings: The Fellowship of the Ring" :director "Peter Jackson" :year 2001 :genre "Adventure" :length 178 :rating "PG-13"}

   {:title "Frozen" :director "Jennifer Lee" :year 2001 :genre "Animation" :length 152 :rating "PG"}

   {:title "Argo" :director "Ben Affleck" :year 2012 :genre "Drama" :length 120 :rating "R"}

   {:title "The Dark Knight" :director "Christopher Nolan" :year 2008 :genre "Action" :length 152 :rating "PG-13"}

   {:title "The Prestige" :director "Christopher Nolan" :year 2006 :genre "Drama" :length 130 :rating "R"}

   {:title "The Big Lebowski" :director "Joel Coen" :year 1998 :genre "Comedy" :length 117 :rating "R"}])

;~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

(defn movie-object-has-pair? [k v movie-object]
  (= v (k movie-object)))

(defn get-movie-object [movie-title]
  (first (filter #(movie-object-has-pair? :title movie-title %) movie-collection)))
