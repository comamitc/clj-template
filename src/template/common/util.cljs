(ns template.common.util
  (:require [cognitect.transit :as t]))

;; @TODO: standardize to stringify keywords.
(defn cljs->js
  "takes native cljs data structure and converts to json"
  [x]
  (let [w (t/writer :json-verbose)]
    (.parse js/JSON (t/write w x))))

(defn js->cljs [x]
  (let [r   (t/reader :json)
        str (if-not (string? x) (.stringify js/JSON x) x)]
    (t/read r str)))
