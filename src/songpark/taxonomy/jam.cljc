(ns songpark.taxonomy.jam
  (:require [clojure.spec.alpha :as spec]))


(spec/def :jam/id uuid?)
(spec/def :jam/status boolean?)
(spec/def :jam.ask/status boolean?)

(spec/def :jam/response (spec/keys :req [:jam/status
                                         :jam/id]))


(spec/def :jam/ask (spec/keys :req [:teleporter/id]))
(spec/def :jam/asked (spec/keys :req [:jam.ask/status]))
(spec/def :jam/obviate (spec/keys :req [:teleporter/id]))
(spec/def :jam/obviated (spec/keys :req [:jam.ask/status]))

(spec/def :jam/stop (spec/keys :req [:jam/id]))
(spec/def :jam/stopped (spec/keys :req [:jam/id
                                        :jam/status]))

(spec/def :jammer/status #{:knocking :jamming})
