(ns bug-playground.core
  (:use [aleph http]
        [ring.middleware multipart-params]))

(def form "<html><body><form method='POST' enctype='multipart/form-data' action='.'> <input type=file name=upfile><input type=submit></form></body></html>")

(def handler
  (-> (fn [request]
        {:status 200
         :headers {"content-type" "text/html"}
         :body form})
      (wrap-multipart-params {:encoding "UTF-8"})))

(defn start []
  (start-http-server (wrap-ring-handler handler) {:port 8081}))
