(ns test-project.core-test
  (:require [expectations :refer :all]
            [test-project.core :refer :all]))

(expect 5 (+ 2 3))
