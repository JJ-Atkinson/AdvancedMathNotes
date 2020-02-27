(ns app.navigate
  (:require [app.utils :as u]
            [app.signals-and-systems.notes1]
            [reagent.core :as r]
            [app.data-model :as dm]
            
            
            
            [app.signals-and-systems.w5_c2]))




(defn page-nav []
  (let [pages @u/note-list]
    [:div>ul
     (map (fn [{:keys [title page-tag]}]
            [:li [:a {:href "#" :onClick #(dm/navigate page-tag)} title]])
          pages)]))

(defn root-view []
  (if (= @dm/view :navigator)
    [page-nav]
    [(u/lookup-view-fn-by-tag @dm/view)]))