#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn extract-digits [input-string]
  (when (not (nil? input-string))
    (Integer/parseInt (str/replace input-string #"\D" ""))))

(defn read-file [file]
  (-> file slurp (str/split-lines)))

(def input "day2/input.txt")
(def example "day2/example_1.txt")

(def input-lines (read-file input))
(def example-lines (read-file example))

(defn find-cube [color cube-line]
  (let [cubes (str/split cube-line #",")]
    (some #(when (str/includes? % color) %) cubes)))

(defn extract-as-list [cube-set]
  (let [blue (extract-digits (find-cube "blue" cube-set))
        red (extract-digits (find-cube "red" cube-set))
        green (extract-digits (find-cube "green" cube-set))]
    (and
     (or (nil? red) (<= red 12))
     (or (nil? green) (<= green 13))
     (or (nil? blue) (<= blue 14)))))

(defn game [line] (extract-digits (first (str/split line #":"))))

(defn game-cube-sets [line]
  (let [sets (last (str/split line #":"))]
    (str/split sets #";")))

(defn sum-sets [acc val]
  (and acc (extract-as-list val)))

(defn summa-line [line]
  (let [id (game line)
        sets (game-cube-sets line)
        all-possible (reduce sum-sets true sets)]
    (if all-possible id 0)))

(defn sum-line [acc val]
  (+ acc (summa-line val)))

(defn sum-lines [lines]
  (reduce sum-line 0 lines))

(println "Example: " (sum-lines example-lines))

(println "Input: " (sum-lines input-lines))