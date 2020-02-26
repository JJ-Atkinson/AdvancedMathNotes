(ns app.views
  (:require [app.state :refer [app-state]]
            [reagent.core :as r]
            [app.events :refer [increment decrement]]
            ["mathjs" :as mjs]
            ["react-mathjax2" :as react-jax]))

(defn parse-math [text]
  (mjs/parse text))

(defn to-tex [expression]
  (.toTex expression #js {:parenthesis "keep"}))


(defn math-node [& {:keys [mode content inline?] :or {mode :jax inline? false}}]
  [:> react-jax/Context
   {:input "tex"}
   [:div 
    [:> react-jax/Node (if inline? {:inline ""} {}) content]]])

(defn markdown-node [str]
  )

(defn header
  []
  [:div
   [:h1 "A template for reagent apps"]
   [:div
    "hi"
    [math-node :content (-> "4pi*phi" parse-math to-tex)
     :inline? true]]

   ])

(defn counter
  []
  [:div
   [:button.btn {:on-click #(decrement %)} "-"]
   [:button {:disabled true} (get @app-state :count)]
   [:button.btn {:on-click #(increment %)} "+"]])





(defn app []
  [:div
   [header]
   [counter]])
