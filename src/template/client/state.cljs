(ns template.client.state)

(defonce app-state (atom {:text  nil}))

;; set the app state
(defn app-state! [keyvec v] (swap! app-state assoc-in keyvec v))
