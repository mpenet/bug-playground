(ns bug-playground.twstream
  (use [lamina core]
       [aleph http formats]))


(def works "follow=14942630&track=bieber")
(def breaks "follow=14942630&track=APPLEPIENOGOOD")

(defn -main
  [& args]
  (let [ch (:body
            (sync-http-request
             {:method :post
              :basic-auth ["aleph_example" "_password"]
              :headers {"content-type" "application/x-www-form-urlencoded"}
              :url "https://stream.twitter.com/1/statuses/filter.json"
              :body works
              :delimiters ["\r"]}))]

    (doseq [tweet (map decode-json (lazy-channel-seq ch))]
      (println tweet))))
