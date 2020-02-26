(ns app.views
  (:require [app.state :refer [app-state]]
            [reagent.core :as r]
            [app.events :refer [increment decrement]]
            ["mathjs" :as mjs]
            ["react-mathjax2" :as react-jax]
            [markdown-to-hiccup.core :as mdc]))

(defn parse-math [text]
  (mjs/parse text))

(defn to-tex [expression]
  (.toTex expression #js {:parenthesis "keep"}))


(defn math-node [& {:keys [mode content inline?] :or {mode :jax inline? false}}]
  [:div {:class (if inline? "inline" "")}
   [:> react-jax/Context
    {:input "tex"}
    [:div
     [:> react-jax/Node content]]]])

(defn markdown-node [str]
  [:div.md-content (mdc/md->hiccup str)])

(defn header
  []
  [:div.flex.flex-row.flex-nowrap.w-100 
   [:h1.w-75 "Math notes for the ages"]
   [:a.w-25 "To index"]])


(defn first-note []

  [:div
   [markdown-node "hello world"]
   [:div [math-node :content (-> "5/(4pi*phi)" parse-math to-tex)
          :inline? false]]
   [markdown-node "Hello. This is the `first` markdown I've written in a _while_"]])







(defn app []
  [:div.full-page-width.center-content-horizontal
   [:div.max-width-960.helvetica
    [header]
    [first-note]
    ]])
