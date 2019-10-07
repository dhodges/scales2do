(ns scales2do.service-worker
  (:import [java.lang.Throwable])
  (:require [clojure.string :as str]))

(def app-cache-name "scales2do")

(def files-to-cache ["/css/stylesvg.css"
                     "/js/main.js"
                     ])

(defn get-from-cache [request]
  (.match js/caches request))

(defn fetch-and-cache [request]
  (-> (js/fetch request)
      (.then (fn [response]
               (-> js/caches
                   (.open app-cache-name)
                   (.then (fn [cache] (.put cache request (.clone response))))
                   (.then (fn [] response)))))
      (.catch (fn [err]
                (js/console.error err)))))

(defn fetch [e]
  (let [request (.-request e)]
    (js/console.log "[ServiceWorker] fetching" (.-url request))
    (or (fetch-and-cache request)
        (get-from-cache request))))

(defn purge-old-caches [e]
  (-> js/caches
      .keys
      (.then (fn [keys]
               (->> keys
                    (map #(when-not (contains? #{app-cache-name} %)
                            (.delete js/caches %)))
                    clj->js
                    js/Promise.all)))))

(defn install-service-worker [e]
  (js/console.log "[ServiceWorker] installing...")
  (-> js/caches
      (.open app-cache-name)
      (.then (fn [cache]
               (js/console.log "[ServiceWorker] caching files")
               (.addAll cache (clj->js files-to-cache))))
      (.then (fn []
               (js/console.log "[ServiceWorker] installed")))))

(.addEventListener js/self "install" #(.waitUntil % (install-service-worker %)))
(.addEventListener js/self "fetch" #(.respondWith % (fetch %)))
(.addEventListener js/self "activate" #(.waitUntil % (purge-old-caches %)))
