(ns guestbook.web
  (:use [ring.adapter.jetty]
        [guestbook.handler :refer [app]))

(defn get-handler []
  ;; #'app expands to (var app) so that when we reload our code,
  ;; the server is forced to re-resolve the symbol in the var
  ;; rather than having its own copy. When the root binding
  ;; changes, the server picks it up without having to restart.
  (-> #'app
    ; Makes static assets in $PROJECT_DIR/resources/public/ available.
    (wrap-file "resources")
    ; Content-Type, Content-Length, and Last Modified headers for files in body
    (wrap-file-info)))

(defn -main []
  (run-jetty (get-handler) {:port (Integer/parseInt (or (System/getenv "PORT") "8080"))}))
