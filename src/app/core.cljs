(ns app.core
  (:require [reagent.core :as r]
            [app.views :as views]
            ["mathjs" :as mjs]))

(defn ^:dev/after-load start
  []
  (r/render-component [views/app]
                      (.getElementById js/document "app")))

(defn ^:export main
  []
  (start))
