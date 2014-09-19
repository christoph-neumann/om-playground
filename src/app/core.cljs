(ns app.core
  (:require [om.core :as om :include-macros true]
            [sablono.core :as html :refer-macros [html]]))

;; Sends "println" to the Javascript console
(enable-console-print!)

(def app-state (atom {:text "Hello Turkey"}))

(defn- root-frame
  [{:keys [text] :as app-state} owner]
  (reify
    om/IInitState
    (init-state [_]
      {:tag "green"})
    om/IWillMount
    (will-mount [_]
      (println "will mount"))
    om/IDidMount
    (did-mount [_]
      (println "did mount"))
    om/IRenderState
    (render-state [_ {:keys [tag] :as state}]
      (html [:div {:ref "top"
                   :className "TopView Box"
                   :style {:background-color "#ccccff"}}
              [:h1 text]
              [:button tag]]))))

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
