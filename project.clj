(defproject guestbook "0.1.0-SNAPSHOT"
  :min-lein-version "2.0.0"
  :description "Guestbook study application"
  :url "https://clojure-studies-guestbook.herokuapp.com"
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [compojure "1.1.6"]
                 [hiccup "1.0.5"]
                 [ring-server "0.3.1"]
                 [org.clojure/java.jdbc "0.2.3"]
                 [org.xerial/sqlite-jdbc "3.7.2"]
                 [lib-noir "0.7.6"]
                 [ring/ring-jetty-adapter "1.2.0-beta1"]]
  :plugins [[lein-ring "0.8.12"]]
  :ring {:handler guestbook.handler/app
         :init guestbook.handler/init
         :destroy guestbook.handler/destroy}
  :profiles
  {:uberjar {:main guestbook.web :aot :all}
   :production
   {:ring
    {:open-browser? false, :stacktraces? false, :auto-reload? false}}
   :dev
   {:open-browser? false, :dependencies [[ring-mock "0.1.5"] [ring/ring-devel "1.3.1"]]}})
  :uberjar-name "guestbook-standalone.jar"
  :auto-clean false
