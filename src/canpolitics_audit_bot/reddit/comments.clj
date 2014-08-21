(ns canpolitics-audit-bot.reddit.comments
  (:gen-class)
  (:require 
    [org.httpkit.client :as http]
    [clojure.tools.logging :refer [log] :as logging]
    [clojure.data.json :as json]))

(def option-defaults {:timeout 1000
                      :keepalive 30000
                      :user-agent "CanadaPolitics-Audit-Bot"})

(defmulti get-items 
  (fn [x] (type x)))

(defmethod get-items clojure.lang.PersistentArrayMap [data]
  (-> data
      (get :data)
      (get :children)))

(defmethod get-items clojure.lang.PersistentVector [data]
  (vec (map #(-> % (get :data)) data)))

(defmethod get-items :default [data]
  (log :warn (str "No get-items for " (type data)))
  (vector nil))

(defn ^:private handle-response
  [{:keys [status headers body error]}]
  (if error
    (logging/spyf :error (str "Error fetching new comments: " error) [])
    (if (= status 200)
      (get-items (json/read-str body :key-fn keyword))
      (logging/spyf :warn (str "Got status code [" status "] when fetching new comments") []))))

(defn fetch-new 
  ([subreddit] (fetch-new subreddit nil))
  ([subreddit before]
   (log :debug (str "PARAM [before] " before))
   (let [options (merge
                   option-defaults
                  {:query-params (merge { :limit 1000 } (if before {:before before} {}))})]
     (http/get 
       (format "http://www.reddit.com/r/%s/comments.json" subreddit) 
       options
       handle-response))))

(defn get-comment-thread 
  [subreddit link-id comment-id]
  (let
    [options (merge
               option-defaults
               {:query-params {:comment comment-id}})]
    (http/get
      (format "http://www.reddit.com/r/%s/comments/%s.json"
              subreddit
              link-id)
      options
      handle-response)))

(defn was-deleted
  [subreddit link-id comment-id]
  (let
    [thread (get-comment-thread subreddit link-id comment-id)]))

(defn extractLinks [comment]
  (let [cTxt (:body_html (:data comment))]
    (if (and cTxt (> (.length cTxt) 0))
      (let [links (map last (re-seq #"&lt;a href=\"(http\S+)\"/?&gt;" cTxt))]
        (log :debug (str "Extracted " (count links) " links from comment " (:name (:data comment))))
        (if (> (count links) 0)
          {:name (:name (:data comment))
           :links links}
          nil))
      nil)))
