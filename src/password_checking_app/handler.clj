(ns password-checking-app.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(def credentials {:username "Florian" :password "abcd"})

(defn check-credentials [username password credentials]
  (if (= username (:username credentials))
    (if (= password (:password credentials)) "success" "wrong password") "user not found"))

(defroutes app-routes
  (GET "/" [] "Hello World")
  (GET "/login" [username password] (check-credentials username password credentials))
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))
