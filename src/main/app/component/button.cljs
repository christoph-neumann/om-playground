(ns app.component.button
  (:require
    [cljs.core.async :refer [put!]]
    [om.core :as om :include-macros true]
    [sablono.core :as html :refer-macros [html]]))

(defn click!
  [event-ch]
  (put! event-ch [:change-color {}]))

(defn component
  [app-state owner {:keys [event-ch] :as opts}]
  (om/component
    (html [:button {:onClick #(click! event-ch)} "button"])))
