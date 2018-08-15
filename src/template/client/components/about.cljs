(ns template.client.components.about
  (:require [rum.core :as rum]))

(rum/defc About []
  [:div
   [:h1 "About"]
   [:h3 "Edit this and watch it change!"]
   [:a {:href "#/"} "home page"]])
