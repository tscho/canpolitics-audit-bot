(ns canpolitics-audit-bot.db.postgres
  (:gen-class))

  (defn hash-from-env-keys
    [keys prefix]
    (apply merge 
      (map 
        (fn [k] 
          {k (System/getenv (str
                              (clojure.string/upper-case prefix)
                              (clojure.string/upper-case (name k))))})
        keys)))

  (def db
    (merge
      {:classname "org.postgresql.Driver"
       :subprotocol "postgresql"}
      (hash-from-env-keys [:user :password :database :host] "db_")))

  (defn configure
    []
    {:db db})
