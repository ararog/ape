(ns lobos.config
  (:use lobos.connectivity))

(def db
  {:classname "org.sqlite.JDBC"
   :subprotocol "sqlite"
   :subname "./ape.db"
   :create true})

(open-global db)
