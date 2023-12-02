#!/usr/bin/env bb

(require '[clojure.string :as str])

(defn extract-digits [input-string]
  (when (not (nil? input-string))
    (Integer/parseInt (str/replace input-string #"\D" ""))))

(defn extract-digits-or-zero [in]
  (let [num (extract-digits in)]
    (if num num 0)))

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
  (let [blue (extract-digits-or-zero (find-cube "blue" cube-set))
        red (extract-digits-or-zero (find-cube "red" cube-set))
        green (extract-digits-or-zero (find-cube "green" cube-set))]
    [blue red green]))

(defn game [line] (extract-digits (first (str/split line #":"))))

(defn game-cube-sets [line]
  (let [sets (last (str/split line #":"))]
    (str/split sets #";")))

(defn sum-sets [acc val]
  (let [listan (extract-as-list val)
        blue (nth listan 0)
        red (nth listan 1)
        green (nth listan 2)
        old-blue (nth acc 0)
        old-red (nth acc 1)
        old-green (nth acc 2)]
    [(max blue old-blue)
     (max red old-red)
     (max green old-green)]))

(defn summa-line [line]
  (let [sets (game-cube-sets line)
        list-summa (reduce sum-sets [0 0 0] sets)]
    list-summa))

(defn sum-line [acc val]
  (+ acc (reduce * (summa-line val))))

(defn sum-lines [lines]
  (reduce sum-line 0 lines))

(println "Example: " (sum-lines example-lines))

(println "Input: " (sum-lines input-lines))