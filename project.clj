(defproject canpolitics-audit-bot "0.1.0-SNAPSHOT"
  :description "Are you curious about what gets deleted on r/canadapolitics? I am"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-tern "0.1.4-SNAPSHOT"]]
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/tools.logging "0.3.0"]
                 [log4j/log4j "1.2.16"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [yesql "0.4.0"]
                 [org.postgresql/postgresql "9.3-1102-jdbc41"]
                 [http-kit "2.1.18"]
                 [overtone/at-at "1.2.0"]]
  :tern {:migration-dir "migrations"
         :version-table "schema_versions"
         :color true
         :init canpolitics-audit-bot.db.postgres/configure}
  :main canpolitics-audit-bot.core)
