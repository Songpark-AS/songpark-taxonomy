(ns songpark.taxonomy.teleporter
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]))

(def mac-regex #"^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$")
(def ip-regex #"\b(?:(?:2(?:[0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9])\.){3}(?:(?:2([0-4][0-9]|5[0-5])|[0-1]?[0-9]?[0-9]))\b")

(spec/def :teleporter/id uuid?)
(spec/def :teleporter/ip (spec/and string? #(re-matches ip-regex %)))
(spec/def :teleporter/nickname string?)
(spec/def :teleporter/serial string?)

(spec/def :teleporter/teleporter
  (spec/keys :req [:teleporter/id
                   :teleporter/nickname]))

(spec/def :teleporter/init
  (spec/keys :req [:teleporter/serial]))


(spec/def :teleporter/ids
  (spec/coll-of (spec/keys :req [:teleporter/id])))


(spec/def :teleporter/settings (spec/keys :req [:teleporter/id
                                                :teleporter/nickname]))
