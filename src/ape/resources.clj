(ns ape.resources
  (:require [liberator.core :refer [defresource by-method]]
            [liberator.representation :refer [ring-response]]
            [clj-time.core :as time]
            [ape.database :as aped]
            [ape.config :as apec]
            [buddy.sign.jws :as jws]
            [buddy.auth :refer [authenticated?]]))

(defn ok [d] (ring-response {:status 200 :body d}))
(defn bad-request [d] (ring-response {:status 400 :body d}))
(defn secured-resource [m]
  {:authorized?    #(authenticated? (:request %))})

(defresource me
  (secured-resource {})
  :available-media-types ["application/json"]
  :allowed-methods       [:get]
  :handle-ok             (fn [ctx]
    (let [id	 (get-in ctx [:request :identity :user])
          user (aped/get-user-by-id id)]
      (println username)
      (ok {:user user}))))

(defresource signin
  :available-media-types ["application/json"]
  :allowed-methods       [:post]
  :handle-created        (fn [ctx]
    (let [username (get-in ctx [:request :body :username])
          password (get-in ctx [:request :body :password])
          user  	 (aped/get-user-by-credentials username password)]
      (if user
        (let [claims {:user (get-in user [:id])
                      :exp (time/plus (time/now) (time/seconds 3600))}
              token (jws/sign claims apec/secret {:alg :hs512})]
          (ok (merge {:user user} {:token token})))
        (bad-request {:message "wrong auth data"})))))
