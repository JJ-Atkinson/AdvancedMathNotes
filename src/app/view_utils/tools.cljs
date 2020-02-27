(ns app.view-utils.tools
  (:require [app.state :refer [app-state]]
            [reagent.core :as r]
            [app.events :refer [increment decrement]]
            ["mathjs" :as mjs]
            ["react-mathjax2" :as react-jax]
            [markdown.core :as mdc]
            [app.data-model :as dm]))

(defn parse-math [text]
  (mjs/parse text))

(defn to-tex [expression]
  (.toTex expression #js {:parenthesis "keep"}))


(defn math-node [{:keys [mode content inline?] :or {mode :jax inline? false}}]
  [:div {:class (if inline? "inline" "")}
   [:> react-jax/Context
    {:input (name mode)}
    [:div
     [:> react-jax/Node (if inline? {:inline ""} {}) content]]]])

(defn markdown-node [str]
  [:div.md-content {:dangerouslySetInnerHTML {:__html (mdc/md->html str)}}])

(defn element? [e] (string? e))

(defn tagify [ss]
  (loop [items ss ret []]
    (let [[tags rst] (split-with (complement element?) items)
          item (first rst)
          rst (rest rst)
          nret (conj ret {:item item :tags (set tags)})]
      (if-not (empty? rst)
        (recur rst nret)
        nret))))


(defn freehand-note-content [strs]
  [:div.note-content
   (map
     (fn [{:keys [tags item]}]
       (condp #(contains? %2 %1) tags
         :form [math-node {:content item
                           :inline? (contains? tags :inline)
                           :mode    (if (contains? tags :tex) :tex :ascii)}]
         [markdown-node item]))
     (tagify strs))])


(defn header
  [title]
  [:div.flex.flex-row.flex-nowrap.w-100
   [:h1.w-75 title]
   [:a.w-25 {:href "#" :onClick #(dm/nav-home)} "To index"]])

(defn default-notes-view [title content-fn]
  [:div.full-page-width.center-content-horizontal
   [:div.max-width-960.helvetica
    [header title]
    [content-fn]
    ]])