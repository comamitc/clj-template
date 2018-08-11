(defproject template "0.1.0-SNAPSHOT"
  :dependencies [[org.clojure/clojure "1.9.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [org.clojure/core.async  "0.4.474"]
                 [rum "0.11.2"]
                 [binaryage/devtools "0.6.1"]
                 [ring "1.7.0-RC1"]
                 [ring-transit "0.1.6"]
                 [ring/ring-defaults "0.2.1"]
                 [com.ninjudd/ring-async "0.2.0"]
                 [secretary "1.2.3"]
                 [com.cognitect/transit-cljs "0.8.256"]
                 [environ "1.0.3"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/tools.logging "0.3.1"]
                 [org.slf4j/slf4j-log4j12 "1.7.1"]
                 [cljs-http "0.1.45"]]

  :plugins [[lein-npm "0.6.2"]
            [lein-figwheel "0.5.16"]
            [lein-cljsbuild "1.1.7" :exclusions [[org.clojure/clojure]]]
            [lein-environ "1.0.3"]
            [lein-ring "0.12.4"]]

  :npm {:dependencies [[source-map-support "0.4.0"]
                       [ws "0.8.1"]]}

  :min-lein-version "2.8.1"

  :source-paths ["src"]

  :clean-targets ^{:protect false} ["resources/public/js/compiled" "target"]

  :template-additions [".gitignore" "README.md"]

  :cljsbuild
  {:builds
   [{:id           "dev"
     :source-paths ["src/template/client" "src/template/common"]
     :figwheel     {:on-jsload "template.client.core/on-js-reload"
                    :open-urls ["http://localhost:3000"]}
     :compiler     {:main                 template.client.core
                    :output-to            "resources/public/js/compiled/app.js"
                    :output-dir           "resources/public/js/compiled/out"
                    :asset-path           "js/compiled/out"
                    :source-map-timestamp true}}

    {:id           "client-min"
     :source-paths ["src/template/client"]
     :compiler     {:main            template.client.core
                    :output-to       "resources/public/js/compiled/app.js"
                    :optimizations   :advanced
                    :closure-defines {goog.DEBUG false}
                    :pretty-print    false}}]}

  :figwheel {:init     user/start-server
             :destroy user/stop-server}

  :ring {:handler template.server.handler/dev-app}

  :aliases {"template" ["do" ["clean"] ["create-template rummy"]]
            "prod"  ["do"  ["clean"]
                           ["cljsbuild" "once" "release"]]}

  :profiles {:dev {:env {:index-file "index.html"}
                   :dependencies [[binaryage/devtools "0.9.10"]
                                  [figwheel-sidecar "0.5.16"]
                                  [cider/piggieback "0.3.8"]
                                  [ring-server "0.5.0"]]
                   :source-paths ["src" "dev"]
                   :repl-options {:nrepl-middleware [cemerick.piggieback/wrap-cljs-repl]}}})
