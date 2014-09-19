(ns app.component.button
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

(def colors ["red" "green" "blue"])

(defn click!
  [app-state]
  (om/update! app-state :color (get colors (rand-int 3))))

(defn component
  [app-state owner opts]
  (om/component
    (html [:button {:onClick #(click! app-state)} "button"])))
