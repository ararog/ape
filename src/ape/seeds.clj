(ns ape.seeds
  (:require [ape.database :as db])
  (:use korma.db
        korma.core))

(defn seed-users! []
  (insert db/users
    (values
      {:name "Rogerio Pereira Araujo" :email "rogerio.araujo@gmail.com" :password "1978@rpa"})))
