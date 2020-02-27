(ns app.core
  (:require [reagent.core :as r]
            [app.views :as views]
            [app.navigate :as n]))

(defn ^:dev/after-load start
  []
  (r/render-component [n/root-view]
                      (.getElementById js/document "app")))

(defn ^:export main
  []
  (start))
