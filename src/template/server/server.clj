(ns template.server.server
  (:require [clj-s.template.server.handler :refer [handler]]
            [config.core :refer [env]]
            [ring.adapter.jetty-async :refer [run-jetty-async]])
  (:gen-class))

(defn -main [& args]
  (let [port (Integer/parseInt (or (env :port) "3000"))]
    (run-jetty-async handler {:port port :join? false})))
