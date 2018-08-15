(ns template.client.components.app
  (:require [rum.core :as rum]
            [template.client.state :refer [app-state]]))

(rum/defc App < rum/reactive []
  (let [*page (rum/cursor-in app-state [:page])]
    [:main ((rum/react *page))]))
