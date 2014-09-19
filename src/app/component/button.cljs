(ns app.component.button
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(def colors ["red" "green" "blue"])

(defn color-changer
  [old]
  (loop []
    (let [color (get colors (rand-int 3))]
      (if (not= color old)
        color
        (recur)))))

(defn click!
  [app-state]
  (om/transact! app-state :color color-changer))

(defn component
  [app-state owner opts]
  (om/component
    (html [:button {:onClick #(click! app-state)} "button"])))
