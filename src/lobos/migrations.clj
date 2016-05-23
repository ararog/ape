(ns lobos.migrations
  (:refer-clojure :exclude [alter drop
                            bigint boolean char double float time])
  (:use (lobos [migration :only [defmigration]] core schema
               config helpers)))

(defmigration add-users-table
  (up [] (create
          (tbl :users
            (varchar :name 50)
            (varchar :email 100 :unique)
            (varchar :password 20))))
  (down [] (drop (table :users))))
