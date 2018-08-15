(ns template.client.components.home
  (:require [rum.core :as rum]))

(rum/defc Home []
  [:div
   [:h1 "Home"]
   [:h3 "Edit this and watch it change!"]
   [:a {:href "#/about"} "about page"]])
