(ns app.signals-and-systems.notes1
  (:require [app.view-utils.tools :refer [freehand-note-content default-notes-view]]
            [app.utils :as u]))


(defn view []
  [freehand-note-content
   [""]])


(u/register-note "First note" 
                 :first-note
                 (constantly [default-notes-view "First Note" view]))
