(ns canpolitics-audit-bot.component
 (:gen-class))

(defprotocol Component
 (start [component])
 (stop [component]))
