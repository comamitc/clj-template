(ns template.client.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [rum.core :as rum]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<! timeout]]))

(enable-console-print!)

(println "This text is printed from src/figgy/core.cljs. Go ahead and edit it and see reloading in action.")

;; define your app data so that it doesn't get over-written on reload

(defonce app-state (atom {:text ""}))

(rum/defc hello-world < rum/reactive []
  [:div
   [:h1 (:text (rum/react app-state))] ;; TODO: use a cursor
   [:h3 "Edit this and watch it change!"]])

(rum/mount (hello-world)
           (. js/document (getElementById "app")))

(defn on-js-reload [])
;; optionally touch your app-state to force rerendering depending on
;; your application
;; (swap! app-state update-in [:__figwheel_counter] inc)

(go
    (let [response (<! (http/get "/hello"))]
      (swap! app-state assoc :text (:body response))))
