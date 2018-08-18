(ns template.client.routes
  (:require [secretary.core :as sec
             :include-macros true]
            [rum.core :as rum]
            [goog.events :as events]
            [goog.history.EventType :as EventType]
            [template.client.state :refer [app-state!]]
            [template.client.components.core :as core])
  (:import goog.History))

(defn init []
  (sec/set-config! :prefix "#")

  (sec/defroute "/" []
    (app-state! [:page] core/Home))

  (sec/defroute "/about" []
    (app-state! [:page] core/About))

  ; setup history API
  (let [history (History.)
        navigation EventType/NAVIGATE]
    (goog.events/listen history
                       navigation
                       #(-> % .-token sec/dispatch!))
    (doto history (.setEnabled true))))
