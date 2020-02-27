(ns app.data-model
  (:require [reagent.core :as r]))


(defonce view (r/atom :navigator))

(defn navigate [location]
  (reset! view location))

(defn nav-home [] (navigate :navigator))
