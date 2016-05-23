(ns ape.database
  (:use korma.db
        korma.core))

(def db (sqlite3 {:db "./ape.db"}))

(defdb korma-db db)

(declare users)

(defentity users
  (pk :id)
  (table :users)
  (database db))

(defn get-user-by-id [id]
  (first (select users
               (fields :id :name :email :created_on :updated_on)
               (where {:id id})
               (limit 1))))

(defn get-user-by-email [email]
  (first (select users
               (fields :id :name :email :created_on :updated_on)
               (where {:email email})
               (limit 1))))

(defn get-user-by-credentials [email password]
  (first (select users
               (where (and (= :email email)
                           (= :password password)))
               (limit 1))))
