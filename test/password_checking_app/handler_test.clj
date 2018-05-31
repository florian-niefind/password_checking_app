(ns password-checking-app.handler-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [password-checking-app.handler :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))

(deftest test-credentials-check

  (testing "wrong user"
    (let [creds {:username "usr" :password "pw"}
          res (check-credentials "a" "pw" creds)]
      (is (= res "user not found"))))

  (testing "wrong password"
    (let [creds {:username "usr" :password "pw"}
          res (check-credentials "usr" "px" creds)]
      (is (= res "wrong password"))))

  (testing "success"
    (let [creds {:username "usr" :password "pw"}
          res (check-credentials "usr" "pw" creds)]
      (is (= res "success")))))
