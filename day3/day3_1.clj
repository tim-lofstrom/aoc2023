#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn extract-digits [input-string]
  (when (not (nil? input-string))
    (Integer/parseInt (str/replace input-string #"\D" ""))))

(defn read-file [file]
  (-> file slurp (str/split-lines)))

(def input "day3/input.txt")
(def example "day3/example_1.txt")

(def input-lines (read-file input))
(def example-lines (read-file example))

(println "Example: " (sum-lines example-lines))

(println "Input: " (sum-lines input-lines))