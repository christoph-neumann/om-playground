(ns app.core
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]
            [app.component.pad :as pad]
            [app.component.button :as button]))


;; Sends "println" to the Javascript console
(enable-console-print!)

(def app-state (atom {:color "blue"}))

(defn- root-frame
  [{:keys [text] :as app-state} owner]
  (reify
    om/IWillMount
    (will-mount [_]
      (println "will mount"))
    om/IRenderState
    (render-state [_ _]
      (html [:div
              (om/build pad/component app-state {})
              (om/build button/component app-state {})]))))

(defn- root-frame-simple
  [app owner]
  (om/component
    (html [:h1 (:text app)])))

(defn- install-root!
  [app-state]
  (let [options {:target (. js/document (getElementById "app"))}]
    (om/root root-frame app-state options)))

(defn- main
  []
  (println "Welcome to the app")
  (install-root! app-state))

;; Set the window.onload property to point to the main function
(set! (.-onload js/window) main)
