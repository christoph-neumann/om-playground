(defproject om-playground "0.1.0-SNAPSHOT"
  :description "A bunch of Om-related experiments."
  :url "https://github.com/christoph-neumann/om-playground"

  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/clojurescript "0.0-2850"]
                 [org.clojure/core.async "0.1.346.0-17112a-alpha"]
                 [org.omcljs/om "0.8.8"]
                 [figwheel "0.2.5-SNAPSHOT"]
                 [sablono "0.3.4"]]

  :plugins [[lein-cljsbuild "1.0.4"]
            [lein-figwheel "0.2.5-SNAPSHOT"]]

  :source-paths ["src/main"]

  :clean-targets ^{:protect false} ["resources/public/app.js" "resources/public/out"]

  :cljsbuild {
    :builds [{:id "dev"
              :source-paths ["src/main" "src/dev"]
              :compiler {:output-to "resources/public/app.js"
                         :output-dir "resources/public/out"
                         :optimizations :none
                         :main app.dev
                         :asset-path "out"
                         :source-map true
                         :source-map-timestamp true
                         :cache-analysis true }}
             {:id "release"
              :source-paths ["src/main"]
              :compiler {:output-to "resources/public/app.js"
                         :main app.core
                         :optimizations :advanced
                         :pretty-print false}}]}

  :figwheel {
             :http-server-root "public" ;; default and assumes "resources"
             :server-port 3449 ;; default
             :css-dirs ["resources/public/css"] ;; watch and update CSS

             ;; Start an nREPL server into the running figwheel process
             ;; :nrepl-port 7888

             ;; Server Ring Handler (optional)
             ;; if you want to embed a ring handler into the figwheel http-kit
             ;; server, this is simple ring servers, if this
             ;; doesn't work for you just run your own server :)
             ;; :ring-handler hello_world.server/handler

             ;; To be able to open files in your editor from the heads up display
             ;; you will need to put a script on your path.
             ;; that script will have to take a file path and a line number
             ;; ie. in  ~/bin/myfile-opener
             ;; #! /bin/sh
             ;; emacsclient -n +$2 $1
             ;;
             ;; :open-file-command "myfile-opener"

             ;; if you want to disable the REPL
             ;; :repl false

             ;; to configure a different figwheel logfile path
             ;; :server-logfile "tmp/logs/figwheel-logfile.log"
             })
