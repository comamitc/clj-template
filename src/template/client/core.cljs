(ns template.client.core
  (:require-macros [cljs.core.async.macros :refer [go]])
  (:require [rum.core :as rum]
            [cljs-http.client :as http]
            [cljs.core.async :refer [<! timeout]]
            [template.client.routes :as routes]
            [template.client.state :refer [app-state]]
            [template.client.components.core :refer [App]]
            [template.common.util :refer [by-id log js-log]]
            [cljss.core :as css]))

(enable-console-print!)

(routes/init!)

(defn- render-app! []

  (rum/mount (App)
             (by-id "app")))

;; figwheel hook
(defn on-js-reload []
  (css/remove-styles!) ;; for cljss
  (render-app!))

(render-app!)
