(ns canpolitics-audit-bot.comments-grabber.grabber
  (:gen-class)
  :require [[canpolitics-audit-bot.reddit.comments :as reddit-comments]])

(defrecord CommentsGrabber [database]
  (start [component])
  (stop [component]))
