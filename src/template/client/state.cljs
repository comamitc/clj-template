(ns template.client.state
  (:require [template.client.components.core :refer [Home]]))

(defonce app-state (atom {:text  nil
                          :page Home}))

(defn app-state! [keyvec v] (swap! app-state assoc-in keyvec v))
