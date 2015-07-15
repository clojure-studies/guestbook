(ns guestbook.web
  (:use [ring.adapter.jetty]
        [ring.middleware.resource]
        [guestbook.handler]))

(defn get-handler []
  ;; #'app expands to (var app) so that when we reload our code,
  ;; the server is forced to re-resolve the symbol in the var
  ;; rather than having its own copy. When the root binding
  ;; changes, the server picks it up without having to restart.
  (-> #'app
    (wrap-resource "public")
    (wrap-resource "/META-INF/resources")))

(defn -main []
  (init)
  (run-jetty (get-handler) {:port (Integer/parseInt (or (System/getenv "PORT") "8080"))}))
