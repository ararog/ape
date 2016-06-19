(ns ape.web
  (:require [compojure.route :as route]
            [compojure.core :refer :all]
            [compojure.response :refer [render]]
            [clojure.java.io :as io]
            [ring.util.response :refer [response redirect content-type]]
            [ring.middleware.session :refer [wrap-session]]
            [ring.middleware.params :refer [wrap-params]]
            [ring.middleware.json :refer [wrap-json-response wrap-json-body]]
            [ring.adapter.jetty :as jetty]
            [ape.resources :as aper]
            [ape.config :as apec]
            [buddy.auth :refer [authenticated? throw-unauthorized]]
            [buddy.auth.backends.token :refer [jws-backend]]
            [buddy.auth.middleware :refer [wrap-authentication wrap-authorization]])
  (:gen-class))

(defroutes app
  (ANY "/users/me" [] aper/me)
	(ANY "/auth/signup" [] aper/signup)
  (ANY "/auth/signin" [] aper/signin))

(def auth-backend (jws-backend {:secret apec/secret :options {:alg :hs512}}))

(defn -main
  [& args]
  (as-> app $
    (wrap-authorization $ auth-backend)
    (wrap-authentication $ auth-backend)
    (wrap-json-response $ {:pretty false})
    (wrap-json-body $ {:keywords? true :bigdecimals? true})
    (jetty/run-jetty $ {:port 3000})))
