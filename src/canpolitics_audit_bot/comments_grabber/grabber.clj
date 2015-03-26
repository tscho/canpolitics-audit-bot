(ns canpolitics-audit-bot.comments-grabber.grabber
  (:gen-class)
  (:require 
    [canpolitics-audit-bot.reddit.comments :as reddit]
    [canpolitics-audit-bot.component :refer [Component]]
    [overtone.at-at :as atat]))

(defrecord CommentsGrabber [database]
  Component
  (start [component]
    (-> component
        (assoc :sched-pool (atat/mk-pool))
        (assoc :db         database)))
  (stop [component]
    (atat/stop-and-reset-pool! (:sched-pool component))))
