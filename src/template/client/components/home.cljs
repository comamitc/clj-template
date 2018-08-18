(ns template.client.components.home
  (:require [rum.core :as rum]
            [cljss.core :as css :refer-macros [defstyles]]))

(defstyles home-styles []
  {:font-size "1.2em"})

(rum/defc Home []
  [:div {:class (home-styles)}
   [:h1 "Home"]
   [:h3 "Edit this and watch it change!"]
   [:a {:href "#/about"} "about page"]])
