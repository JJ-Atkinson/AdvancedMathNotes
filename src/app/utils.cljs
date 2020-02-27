(ns app.utils
  (:require [clojure.string :as str]
            [reagent.core :as r]))


(def query-str
  "Map of qkey : qvalue. {string? string?}"
  (if-let [res (some-> js/window
                       .-location
                       .-href
                       str
                       (str/split "?")
                       second
                       (str/split "#")
                       first
                       (str/split "&")
                       (as-> x
                             (map #(str/split % "=") x)
                             (into {} x)))]
    res
    {}))

(defn map->uri-arg-str [args]
  "Convert an arg map to a uri encode query string"
  (->> args (map (fn [[k v]] (str (js/encodeURIComponent (name k)) \= (js/encodeURIComponent v))))
       (str/join \&)))

(defn request-url
  "Returns the url given a base-link and a map of arguments to be uri encoded."
  [base-link args]
  (str base-link \? (map->uri-arg-str args)))


(def note-list (r/atom []))

(defn register-note [name tag view-fn]
  (swap! note-list #(conj % {:title name 
                             :page-tag tag
                             :view-fn view-fn})))


(defn lookup-view-fn-by-tag [tag]
  (get (into {} (map (fn [{:keys [view-fn page-tag]}]
                       [page-tag view-fn])
                     @note-list))
       tag))
