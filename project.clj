(defproject canpolitics-audit-bot "0.1.0-SNAPSHOT"
  :description "Are you curious about what gets deleted on r/canadapolitics? I am"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6"]
                 [org.clojure/tools.logging "0.2.6"]
                 [log4j/log4j "1.2.16"]
                 [clj-http "0.9.2"]
                 [org.clojure/data.json "0.2.2"]
                 [org.clojure/java.jdbc "0.3.3"]]
  :main canpolitics-audit-bot.core)
