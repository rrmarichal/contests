(ns number-of-islands
  (:require [clojure.set :as set]))

;; The grid becomes a set of land coordinates. Nothing is ever mutated:
;; each step returns a smaller set with one whole island removed.
;;
;; Types used in the signatures below (Clojure doesn't check them; they're notes):
;;
;;   Grid  = a sequence of rows; a row is a string "11000" or a vector ["1" "1" "0" ...]
;;   Cell  = [row col]                 a pair of ints, e.g. [2 3]
;;   Cells = #{Cell}                   a set of cells, e.g. #{[0 0] [0 1] [1 0]}
;;
;; Signatures read like Haskell: `Cells, Cell -> Cells` means "takes a Cells and a
;; Cell, returns a Cells".

;; Grid -> Cells
(defn land-cells
  "Every land cell in the grid."
  [grid]
  (set (for [[r row] (map-indexed vector grid)    ; r: int,  row: string or vector
             [c ch]  (map-indexed vector row)     ; c: int,  ch: \1 / \0 or "1" / "0"
             :when   (#{\1 "1"} ch)]
         [r c])))

;; Cell -> [Cell]   (always exactly 4; some may be off the grid, which is fine
;;                   because those are never in a Cells set)
(defn neighbors [[r c]]
  [[(inc r) c] [(dec r) c] [r (inc c)] [r (dec c)]])

;; Cells, Cells -> Cells
(defn land-neighbors
  "Every cell next to `frontier` that is still in `land`."
  [frontier land]                                  ; frontier: Cells,  land: Cells
  (->> frontier                                    ; Cells
       (mapcat neighbors)                          ; [Cell]  (may contain duplicates)
       (filter (fn [cell] (contains? land cell)))  ; [Cell]  only the ones still land
       set))                                       ; Cells   duplicates removed

;; Cells, Cell -> Cells
(defn sink
  "Removes the island containing `start` from `land`.
   Spreads outwards one ring at a time, deleting each ring as it is reached."
  [land start]                                     ; land: Cells,  start: Cell
  (loop [frontier  #{start}                        ; Cells: the ring reached in the last step
         remaining (disj land start)]              ; Cells: land not reached yet
    (if (empty? frontier)
      remaining                                    ; nothing left to spread to: island gone
      (let [next-frontier (land-neighbors frontier remaining)]   ; Cells
        (recur next-frontier
               (set/difference remaining next-frontier))))))

;; Grid -> int
(defn num-islands
  "Sink an arbitrary island until no land is left; the number of steps is the answer."
  [grid]
  (->> (land-cells grid)                           ; Cells
       (iterate (fn [land] (sink land (first land)))) ; [Cells]  each one island smaller (infinite, lazy)
       (take-while seq)                            ; [Cells]  stop before the empty set
       count))                                     ; int

;; --- examples ---------------------------------------------------------------

(def example-1 [["1" "1" "1" "1" "0"]
                ["1" "1" "0" "1" "0"]
                ["1" "1" "0" "0" "0"]
                ["0" "0" "0" "0" "0"]])

(def example-2 ["11000"
                "11000"
                "00100"
                "00011"])

(println (num-islands example-1))  ; 1
(println (num-islands example-2))  ; 3
(println (num-islands ["0"]))      ; 0
(println (num-islands (repeat 300 (apply str (take 300 (cycle "10"))))))  ; 150
