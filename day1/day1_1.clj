#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn read-file [file]
  (-> file slurp (str/split-lines)))

(defn extract-digits [input-string]
  (str/replace input-string #"\D" ""))

(defn first-and-last [input-string]
  (let [digits (extract-digits input-string)]
    (str (first digits) (last digits))))

(defn sum-line [acc val]
  (+ acc (Integer/parseInt (first-and-last val))))

(defn sum-lines [lines]
  (reduce sum-line 0 lines))

(def input "day1/input.txt")
(def example "day1/example_1.txt")

(def input-lines (read-file input))
(def example-lines (read-file example))

(println "Example: "(sum-lines example-lines))
(println "Input: " (sum-lines input-lines))