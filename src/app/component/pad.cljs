(ns app.component.pad
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))


(defn component
  [{:keys [color] :as app-state} owner opts]
  (om/component
    (html [:p {:style {:color "white"
                       :background-color color
                       :height "30px"}} color])))
