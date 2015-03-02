(ns ^:figwheel-always app.core
  (:require-macros
    [cljs.core.async.macros :refer [go-loop]])
  (:require
    [cljs.core.async :refer [chan <!]]
    [om.core :as om :include-macros true]
    [sablono.core :as html :refer-macros [html]]
    [app.component.pad :as pad]
    [app.component.button :as button]))


;; Sends "println" to the Javascript console
(enable-console-print!)

(def colors ["red" "green" "blue"])

;; defined once so that it doesn't get over-written on reload
(defonce app-state (atom {:color "blue"}))

(defn color-changer
  [old]
  (loop []
    (let [color (get colors (rand-int 3))]
      (if (not= color old)
        color
        (recur)))))

(defn- root-frame
  [{:keys [text] :as app-state} owner]
  (reify
    om/IInitState
    (init-state [_]
      {:events (chan 1)})
    om/IWillMount
    (will-mount [_]
      (println "will mount")
      (go-loop []
        (let [[topic data] (<! (om/get-state owner :events))]
          (println "root >" (pr-str [topic data]))
          (case topic
            :change-color (om/transact! app-state :color color-changer)
            nil)
          (recur))))
    om/IRenderState
    (render-state [_ {:keys [events]}]
      (html [:div
              (om/build pad/component app-state {})
              (om/build button/component app-state {:opts {:event-ch events}})]))))

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
